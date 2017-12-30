package xyz.raieen.cardsapp;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcA;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.raieen.cardsapp.fragment.CardsFragment;

public class MainActivity extends AppCompatActivity {

    private CardsService cardsService;
    // TODO: 2017-12-29 This is temp. Just until accounts are fully working.
    public static String ACCOUNT_ID = "5a45951ccb5bea33384d118e";

    CardsPagerAdapter cardsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);

        cardsPagerAdapter = new CardsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(cardsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //RetroFit Time

        String apiUrl = "http://raieen.xyz:8080/v1/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).callbackExecutor(Executors.newSingleThreadExecutor()).addConverterFactory(GsonConverterFactory.create()).build();
        cardsService = retrofit.create(CardsService.class);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage ndefMessage = (NdefMessage) parcelables[0];

            if (parcelables != null && ndefMessage != null) {
                String cardId = new String(ndefMessage.getRecords()[0].getPayload()).substring(12);
                cardsService.addCard(cardId, RequestBody.create(MediaType.parse("text/plain"), ACCOUNT_ID)).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        CardsFragment cardsFragment = (CardsFragment) cardsPagerAdapter.getItem(0);
                        if (cardsFragment != null) {
                            // TODO: 2017-12-30 Make fragment update. Use interface.
                            cardsFragment.update();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(400);
            }
        }
    }

    public CardsService getCardsService() {
        return cardsService;
    }
}

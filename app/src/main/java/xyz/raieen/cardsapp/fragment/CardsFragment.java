package xyz.raieen.cardsapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.raieen.cardsapp.Card;
import xyz.raieen.cardsapp.CardsService;
import xyz.raieen.cardsapp.MainActivity;
import xyz.raieen.cardsapp.R;
import xyz.raieen.cardsapp.UserAccount;

/**
 * Created by Admin on 2017-12-28.
 */

public class CardsFragment extends Fragment {

    View view;
    MainActivity mainActivity;
    RecyclerView cardsRecyclerView, personalCardsRecycelrView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_cards, container, false);
        cardsRecyclerView = view.findViewById(R.id.cardsRecyclerView);
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        personalCardsRecycelrView = view.findViewById(R.id.personalCardsRecyclerView);
        personalCardsRecycelrView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(personalCardsRecycelrView);

        mainActivity = (MainActivity) getActivity();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        update();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mainActivity != null) {
            update();
        }
    }

    public void update() {
        CardsService cardsService = mainActivity.getCardsService();

        cardsService.getAccountCards(mainActivity.ACCOUNT_ID).enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (response.body() == null || response.body().isEmpty()) {
                    // TODO: 2017-12-29 Handle if cards are null.
                    System.out.println("Cards Null or empty.");
                    return;
                }

                final List<Card> personalCards = new ArrayList<>();
                final List<Card> cards = new ArrayList<>();

                for (Card card : response.body()) {
                    if (card.getOwnerId().equals(MainActivity.ACCOUNT_ID)) {
                        personalCards.add(card);
                    } else {
                        cards.add(card);
                    }
                }

                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cardsRecyclerView.setAdapter(new CardAdapter(cards, getContext()));
                        personalCardsRecycelrView.setAdapter(new CardAdapter(personalCards, getContext()));
                    }
                });
                System.out.println("Update done.");
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                t.printStackTrace();
                // TODO: 2017-12-29 Handle Failure.
            }
        });
    }
}
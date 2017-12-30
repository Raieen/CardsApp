package xyz.raieen.cardsapp;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Admin on 2017-12-28.
 */

public interface CardsService {

    @POST("cards/create")
    Call<String> createCard(@Body Card card);

    @POST("cards/{id}")
    Call<Card> getCard(@Path("id") String id);

    @POST("cards/{id}/modify")
    Call<Card> setCard(@Path("id") String id, @Body Card changes);

    @POST("cards/{id}/delete")
    Call<Card> deleteCard(@Path("id") String id);

    @POST("cards/{id}/add")
    Call<Void> addCard(@Path("id") String cardId, @Body RequestBody requestBody);

    @POST("cards/{id}/remove")
    Call<Void> removeCard(@Path("id") String id, @Body String cardId);

    @POST("account/create")
    Call<String> createAccount(@Body UserAccount userAccount);

    @POST("account/{id}/delete")
    Call<Void> deleteAccont(@Path("id") String id);

    @POST("account/{id}")
    Call<UserAccount> getAccount(@Path("id") String id);

    @POST("account/{id}/cards")
    Call<List<Card>> getAccountCards(@Path("id") String id);
}
package com.example.osamaa.booklist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("v1/volumes")
    Call<List<Book>> listBooks(@Query("q") String q);
}

package com.movies;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface apiset
{
    @GET("json_movie_fetch")
    Call<List<responseModel>> getdata();
}

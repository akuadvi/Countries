package com.example.countries;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface GetService {
    @GET("asia")
    Call<List<countries>> getCountry();
}

package com.creditmantri.webservice;

import com.creditmantri.apiResponse.WheatherResp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by SelvaGanesh on 25-04-2017.
 */

public class RestClient {

    public static APIInterface apiInterface;
    public static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static String api_Token="08d0906bb2579eca5c590ffbd447857419b356a194cdf39dfa6dabc35529734a";


    public static APIInterface getapiclient() {

        if (apiInterface == null) {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            //OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
            OkHttpClient okHttpClient1 = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(130, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(okHttpClient1)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            apiInterface = retrofit.create(APIInterface.class);
        }

        return apiInterface;
    }


    public interface APIInterface {

        @Headers({
                "Content-Type: application/json","x-api-key: d56fdc955bb090f0bec54c095b399089"
        })

        @GET("forecast?")
        Call<WheatherResp> getWhetherResp(@Query("q") String params,@Query("units") String metrics);

    }
}

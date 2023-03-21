package org.receiptscanner.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReceiptsApiClient {
    private static final String API_URL = "https://interview-task-api.mca.dev/";
    private static Retrofit retrofit;
    private static ReceiptsApiService receiptsApiService;

    private Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public ReceiptsApiService getReceiptsApiService() {
        if (receiptsApiService == null) {
            receiptsApiService = getRetrofitInstance().create(ReceiptsApiService.class);
        }
        return receiptsApiService;
    }
}

package org.receiptscanner.api;

import org.receiptscanner.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ReceiptsApiService {
    @GET("/qr-scanner-codes/{receiptCode}")
    Call<List<Product>> getReceipt(@Path("receiptCode") String receiptCode);
}

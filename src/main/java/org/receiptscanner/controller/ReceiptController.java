package org.receiptscanner.controller;

import org.receiptscanner.api.ReceiptsApiService;
import org.receiptscanner.model.Product;
import org.receiptscanner.view.ReceiptView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ReceiptController {
    private final ReceiptsApiService receiptsApiService;
    private final ReceiptView receiptView;

    public ReceiptController(ReceiptsApiService receiptsApiService, ReceiptView receiptView) {
        this.receiptsApiService = receiptsApiService;
        this.receiptView = receiptView;
    }

    public void getReceipt(String receiptCode) {
        receiptsApiService.getReceipt(receiptCode).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (!response.body().isEmpty()) {
                        List<Product> products = response.body();
                        Map<Boolean, List<Product>> groupedProducts = groupProductsBy(products, Product::getDomestic);
                        List<Product> domesticProducts = groupedProducts.get(true);
                        List<Product> importedProducts = groupedProducts.get(false);
                        receiptView.displayReceiptDetails(domesticProducts,
                                importedProducts,
                                sumPrice(domesticProducts),
                                sumPrice(importedProducts));
                    } else {
                        receiptView.displayError("Receipt is empty");
                    }
                } else {
                    receiptView.displayError("Unable to retrieve receipt details");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {
                receiptView.displayError(throwable.getMessage());
            }
        });
    }

    private <T> Map<T, List<Product>> groupProductsBy(List<Product> products, Function<Product, T> getField) {
        Map<T, List<Product>> groupedProducts = new HashMap<>();
        for (Product product : products) {
            T fieldValue = getField.apply(product);
            List<Product> listProducts = groupedProducts.getOrDefault(fieldValue, new ArrayList<>());
            listProducts.add(product);
            Collections.sort(listProducts);
            groupedProducts.put(fieldValue, listProducts);
        }
        return groupedProducts;
    }

    private double sumPrice(List<Product> products) {
        double sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }
}

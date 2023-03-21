package org.receiptscanner.view;

import org.receiptscanner.model.Product;

import java.util.List;

public class ReceiptView {
    public void displayReceiptDetails(
            List<Product> domesticProducts,
            List<Product> importedProducts,
            double domesticProductsCost,
            double importedProductsCost
    ) {
        System.out.println(".Domestic");
        for (Product product : domesticProducts) {
            System.out.println(product.toString());
        }

        System.out.println(".Imported");
        for (Product product : importedProducts) {
            System.out.println(product.toString());
        }

        System.out.println("Domestic cost: " + String.format("$%s", domesticProductsCost));
        System.out.println("Imported cost: " + String.format("$%s", importedProductsCost));

        System.out.println("Domestic count: " + domesticProducts.size());
        System.out.println("Imported count: " + importedProducts.size());
        System.exit(0);
    }

    public void displayError(String errorMessage) {
        System.out.println(errorMessage);
        System.exit(0);
    }
}

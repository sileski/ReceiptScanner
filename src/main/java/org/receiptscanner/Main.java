package org.receiptscanner;

import org.receiptscanner.api.ReceiptsApiClient;
import org.receiptscanner.api.ReceiptsApiService;
import org.receiptscanner.controller.ReceiptController;
import org.receiptscanner.view.ReceiptView;

public class Main {
    public static void main(String[] args) {
        ReceiptsApiService receiptsApiService = new ReceiptsApiClient().getReceiptsApiService();
        ReceiptView receiptView = new ReceiptView();
        ReceiptController receiptController = new ReceiptController(receiptsApiService, receiptView);
        receiptController.getReceipt("alpha-qr-gFpwhsQ8fkY1");
    }
}
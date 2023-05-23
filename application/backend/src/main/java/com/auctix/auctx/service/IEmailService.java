package com.auctix.auctx.service;

import com.auctix.auctx.model.Product;

public interface IEmailService {
    void sendNewProductEmail(String recipientUsername, String adderUsername, String productPosterEmail, Product product);
}

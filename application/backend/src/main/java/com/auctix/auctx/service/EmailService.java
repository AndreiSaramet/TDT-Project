package com.auctix.auctx.service;

import com.auctix.auctx.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Override
    public void sendNewProductEmail(String recipientUsername, String adderUsername, String receiverEmail, Product product) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receiverEmail);
            mimeMessageHelper.setSubject(
                    "A new product has been added to your auction");

            String message = "<h1>Hi " + "<b>" + recipientUsername + "," + "</b></h1>" + "<br>" +
                    "<h1><b>"+adderUsername+ "</b></h1>"+ "Has added a new product to their collection"+ "<br><br>"
                    +"Product name: " + product.getName() + "<br>" +
                    "Price: "+ product.getPrice() + " lei <br>" +
                    "Link to the product: " + "http://localhost:4200/product/" + product.getId() + "<br><br>" +

                    "Thank you for choosing Auctix.<br>" +
                    "Regards,<br>" +
                    "The Auctix Team";

            mimeMessage.setContent(message, "text/html");

            // Sending the mail
            javaMailSender.send(mimeMessage);
        } catch (MessagingException ignored) {

        }
    }
}

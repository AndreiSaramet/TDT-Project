package com.auctix.auctx.controller;

import com.auctix.auctx.model.Product;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.service.EmailService;
import com.auctix.auctx.service.ProductService;
import com.auctix.auctx.service.UserFriendshipService;
import com.auctix.auctx.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailController {
    private final EmailService emailService;

    private final UsersService usersService;

    private final ProductService productService;

    private final UserFriendshipService userFriendshipService;

    @PostMapping("/notifyFriendsNewProductEmail/{adderId}/{productId}")
    public void notifyFriendsNewProductEmail(@PathVariable Long adderId, @PathVariable Long productId) {
        Users adder = usersService.findById(adderId);
        Product product = productService.getProductById(productId);
        List<Users> friends = userFriendshipService.findAllFriends(adder.getId());
        for (Users friend : friends) {
            emailService.sendNewProductEmail(friend.getUsername(),adder.getUsername(), friend.getEmail(), product);
        }
    }
}

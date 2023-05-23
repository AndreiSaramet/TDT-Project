package com.auctix.auctx.controller;


import com.auctix.auctx.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "User not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UserAlreadyExistingException.class})
    protected ResponseEntity<Object> handleUserAlreadyExisting(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "User already exists",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolation(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Validation error",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ProductReviewAlreadyExistingException.class})
    protected ResponseEntity<Object> handleProductReviewAlreadyExisting(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Product review already exists",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({UserReviewAlreadyExistingException.class})
    protected ResponseEntity<Object> handleUserReviewAlreadyExisting(Exception exception, WebRequest request) {
        return this.handleExceptionInternal(exception, "User review already exists", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ProductReviewNotFoundException.class})
    protected ResponseEntity<Object> handleProductReviewNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Product review not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UserReviewNotFoundException.class})
    protected ResponseEntity<Object> handleUserReviewNotFound(Exception exception, WebRequest request) {
        return this.handleExceptionInternal(exception, "User review not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ProductAlreadyExistingException.class})
    protected ResponseEntity<Object> handleProductAlreadyExisting(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Product already exists",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    protected ResponseEntity<Object> handleProductNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Product not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({FriendRequestAlreadyExistingException.class})
    protected ResponseEntity<Object> handleFriendRequestAlreadyExisting(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Friend request already exists",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({FriendRequestNotFoundException.class})
    protected ResponseEntity<Object> handleFriendRequestNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Friend request not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({OwnFriendRequestException.class})
    protected ResponseEntity<Object> handleOwnFriendRequest(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "You can't add yourself",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ReceiverNotFoundException.class})
    protected ResponseEntity<Object> handleReceiverNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Receiver not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({SenderNotFoundException.class})
    protected ResponseEntity<Object> handleSenderNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Sender not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UsersNotFriendsException.class})
    protected ResponseEntity<Object> handleUsersNotFriends(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Users are not friends",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ProductImageNotFoundException.class})
    protected ResponseEntity<Object> handleProductImageNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Product image not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UserProfileNotFoundException.class})
    protected ResponseEntity<Object> handleUserProfileNotFount(Exception exception, WebRequest request) {
        return this.handleExceptionInternal(exception, "User profile not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}

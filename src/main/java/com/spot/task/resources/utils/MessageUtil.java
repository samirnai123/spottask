package com.spot.task.resources.utils;

public interface MessageUtil {

    String SUCCESS = "SUCCESS";
    String ERROR = "ERROR";

    interface SUCCESS_MESSAGE {
        String CARD_VALIDATED_SUCCESSFULLY = "Card validated successfully.";
        String COUNTRY_ADDED = "Block country added successfully.";
        String SUCCESSFULLY_REMOVED = "Block country successfully removed.";
    }

    interface ERRORS{
        String UNAUTHORIZED = "You are not authorized to use this card as it's from our Block countries.";
        String INVALID_CARD = "Invalid card number.";
        String SOMETHING_WENT_WRONG = "Something went wrong";
        String CARD_EXISTS = "Card Already exists.";
        String CARD_NOT_FOUND = "Card not found.";
        String COUNTRY_NOT_FOUND = "Country not found.";
    }
}

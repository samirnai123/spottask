package com.spot.task.services;

import com.spot.task.services.dto.CreditCardDTO;

import java.util.List;

public interface CreditCardService {

    List<CreditCardDTO> validateCreditCard(List<CreditCardDTO> creditCardDTOList);

    List<CreditCardDTO> getAllCards();

    CreditCardDTO getCardById(String cardNumber);
}

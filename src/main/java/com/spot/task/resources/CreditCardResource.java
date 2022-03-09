package com.spot.task.resources;

import com.spot.task.resources.utils.MessageUtil;
import com.spot.task.services.CreditCardService;
import com.spot.task.services.dto.CreditCardDTO;
import com.spot.task.services.generic.GenResponse;
import com.spot.task.services.generic.MyResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CreditCardResource {

    private final Logger log = LoggerFactory.getLogger(CreditCardResource.class);

    private final CreditCardService creditCardService;

    public CreditCardResource(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/credit-card")
    public MyResponseEntity<GenResponse<List<CreditCardDTO>>> validateCreditCard(@Valid @RequestBody List<CreditCardDTO> creditCardDTOList){
        log.debug("Request to validate credit cards");
        List<CreditCardDTO> creditCardDTOS = creditCardService.validateCreditCard(creditCardDTOList);
        return MyResponseEntity.OK(MessageUtil.SUCCESS,MessageUtil.SUCCESS_MESSAGE.CARD_VALIDATED_SUCCESSFULLY,creditCardDTOS);
    }

    @GetMapping("/list/credit-cards")
    public MyResponseEntity<GenResponse<List<CreditCardDTO>>> getAllCreditCards(){
        log.debug("Request to get all credit cards");
        List<CreditCardDTO> creditCardDTOList = creditCardService.getAllCards();
        return MyResponseEntity.OK(MessageUtil.SUCCESS,MessageUtil.SUCCESS,creditCardDTOList);
    }

    @GetMapping("/credit-card/{cardNumber}")
    public MyResponseEntity<GenResponse<CreditCardDTO>> getCreditCardById(@PathVariable String cardNumber){
        log.debug("Request to get credit card by card number");
        CreditCardDTO creditCard = creditCardService.getCardById(cardNumber);
        return MyResponseEntity.OK(MessageUtil.SUCCESS,MessageUtil.SUCCESS,creditCard);
    }

}

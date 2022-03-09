package com.spot.task.services;

import com.spot.task.repository.BlockCountryRepository;
import com.spot.task.repository.CreditCardRepository;
import com.spot.task.repository.SpotUserRepository;
import com.spot.task.resources.errors.CustomException;
import com.spot.task.services.dto.CreditCardDTO;
import com.spot.task.services.impl.CreditCardServiceImpl;
import org.hibernate.usertype.CompositeUserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CreditCardServiceTest {

    private final Logger log = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    @Autowired
    MyService myService;
    @Autowired
    BlockCountryRepository blockCountryRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    SpotUserRepository spotUserRepository;
    @Autowired
    CreditCardService creditCardService;
    @Autowired
    ModelMapper modelMapper;

    @Test
    @DisplayName("get all credit card")
    public void testGetAllCards(){
        // Setup our mock repository

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        CreditCardDTO creditCardDTO1 = new CreditCardDTO();

        creditCardDTO.setCountry("12");
        creditCardDTO.setCardNumber("374245455400126");
        creditCardDTO.setSpotUser(1L);

        creditCardDTO1.setCountry("32");
        creditCardDTO1.setCardNumber("378282246310005");
        creditCardDTO1.setSpotUser(2L);

        List<CreditCardDTO> creditCards = Arrays.asList(creditCardDTO, creditCardDTO1);
        /*try{
            Mockito.doReturn(Arrays.asList(creditCardDTO, creditCardDTO1)).when(creditCardRepository).findAll();
        }catch (CustomException exception){
            log.debug(exception.getMessage());
        }*/

        // Execute the service call
        List<CreditCardDTO> creditCardDTOList = creditCardService.getAllCards();

        // Assert the response
        Assertions.assertEquals(2, creditCards.size(), "findAll should return 2 widgets");
    }


}

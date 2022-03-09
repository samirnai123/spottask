package com.spot.task.services;

import com.spot.task.services.dto.CreditCardDTO;
import com.spot.task.services.impl.CreditCardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CronService {

    private final Logger log = LoggerFactory.getLogger(CronService.class);

    private final CreditCardServiceImpl creditCardService;

    public CronService(CreditCardServiceImpl creditCardService) {
        this.creditCardService = creditCardService;
    }

    /**
     * Cron for processing cards at every seconds
     */
    @Scheduled(cron = "*/30 * * * * *")
    public void processCreditCards(){
        log.debug("working at every 30 seconds");

        List<CreditCardDTO> creditCardDTOList = new ArrayList<>();

        /**
         * Get list of credit cars details and below method is responsible for validating the card and
         * persist in Database
         */
        // Get list of cards portion is left
        /**
         * Method call from CreditCardServiceImpl class
         */
        //creditCardService.validateCardDetailsAndPersistInDatabase(creditCardDTOList);
    }
}

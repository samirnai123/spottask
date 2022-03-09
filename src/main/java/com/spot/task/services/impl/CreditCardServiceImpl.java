package com.spot.task.services.impl;

import com.spot.task.domains.BlockCountry;
import com.spot.task.domains.CreditCard;
import com.spot.task.domains.SpotUser;
import com.spot.task.repository.BlockCountryRepository;
import com.spot.task.repository.CreditCardRepository;
import com.spot.task.repository.SpotUserRepository;
import com.spot.task.resources.errors.CustomException;
import com.spot.task.resources.utils.MessageUtil;
import com.spot.task.services.CreditCardService;
import com.spot.task.services.MyService;
import com.spot.task.services.dto.CreditCardDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    private final Logger log = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    private final MyService myService;
    private final BlockCountryRepository blockCountryRepository;
    private final CreditCardRepository creditCardRepository;
    private final SpotUserRepository spotUserRepository;

    @Autowired
    ModelMapper modelMapper;

    public CreditCardServiceImpl(MyService myService, BlockCountryRepository blockCountryRepository, CreditCardRepository creditCardRepository, SpotUserRepository spotUserRepository) {
        this.myService = myService;
        this.blockCountryRepository = blockCountryRepository;
        this.creditCardRepository = creditCardRepository;
        this.spotUserRepository = spotUserRepository;
    }

    /**
     * Method to validate credit card and save to Database
     * @param creditCardDTOList request credit card model
     * @return list of validated credit cards
     */
    @Override
    public List<CreditCardDTO> validateCreditCard(List<CreditCardDTO> creditCardDTOList) {

        if (creditCardDTOList.size() > 5) {
            // scheduler for process of 5 cards in 30 seconds or less
        }else{
            validateCardDetailsAndPersistInDatabase(creditCardDTOList);
        }
        return null;
    }

    /**
     * Method to validate card number and country verification
     * @param creditCardDTOList list of credit card details
     */
    public void validateCardDetailsAndPersistInDatabase(List<CreditCardDTO> creditCardDTOList) {

        /**
         * Validate card number
         */
        for (CreditCardDTO creditCardDTO : creditCardDTOList) {

            final boolean[] flag = {(creditCardDTO.getCardNumber().length() & 1) == 1};
            boolean result = Arrays.stream(
                            creditCardDTO.getCardNumber().split(""))
                    .map(Integer::parseInt)
                    .mapToInt(value -> value)
                    .map(integer -> ((flag[0] ^= true) ? (integer * 2 - 1) % 9 + 1 : integer))
                    .sum() % 10 == 0;

            if (result) {

                /**
                 * Get country code from credit card number, used Webclient call 3rd party API (binlist)
                 */
                Integer countryCode = myService.getCountryCode(creditCardDTO.getCardNumber());
                if (countryCode != null) {
                    /**
                     * check whether the card is from block countries or not
                     */
                    Optional<BlockCountry> blockCountry = blockCountryRepository.findBlockCountryByCountryCode(countryCode);
                    if (blockCountry.isPresent()) {
                        log.error(MessageUtil.ERRORS.UNAUTHORIZED);
                        throw CustomException.throwMe(MessageUtil.ERROR,MessageUtil.ERRORS.UNAUTHORIZED, Status.BAD_REQUEST);
                    }else{
                        /**
                         * If card is valid, persist it in Database
                         */
                        Optional<CreditCard> cardNumber = creditCardRepository.findCreditCardByCardNumber(creditCardDTO.getCardNumber());
                        if (cardNumber.isPresent()) {
                            log.error(MessageUtil.ERRORS.CARD_EXISTS);
                            throw CustomException.throwMe(MessageUtil.ERROR,MessageUtil.ERRORS.CARD_EXISTS, Status.BAD_REQUEST);
                        }else{
                            creditCardDTO.setCountry(String.valueOf(countryCode));
                            CreditCard creditCard = modelMapper.map(creditCardDTO, CreditCard.class);
                            Optional<SpotUser> spotUser = spotUserRepository.findById(creditCardDTO.getSpotUser());
                            spotUser.ifPresent(creditCard::setSpotUser);
                            creditCardRepository.save(creditCard);
                        }
                    }
                }else {
                    log.error(MessageUtil.ERRORS.SOMETHING_WENT_WRONG);
                    throw CustomException.throwMe(MessageUtil.ERROR,MessageUtil.ERRORS.SOMETHING_WENT_WRONG, Status.BAD_REQUEST);
                }
            }else{
                log.error( MessageUtil.ERRORS.INVALID_CARD + " :: "+ creditCardDTO.getCardNumber());
                throw CustomException.throwMe(MessageUtil.ERROR,MessageUtil.ERRORS.INVALID_CARD, Status.BAD_REQUEST);
            }
        }
    }

    /**
     * Method to Get all list of credit cards
     * @return list of cards
     */
    @Override
    public List<CreditCardDTO> getAllCards() {
        return creditCardRepository.findAll()
                .stream()
                .map(creditCard -> modelMapper.map(creditCard, CreditCardDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Method to get card by it's number
     * @param cardNumber credit card number
     * @return get single card by card number
     */
    @Override
    public CreditCardDTO getCardById(String cardNumber) {

        Optional<CreditCardDTO> creditCardDTO = creditCardRepository.findCreditCardByCardNumber(cardNumber)
                .map(creditCard -> modelMapper.map(creditCard, CreditCardDTO.class));
        if (creditCardDTO.isPresent()) {
            return creditCardDTO.get();
        }else{
            throw CustomException.throwMe(MessageUtil.ERRORS.CARD_NOT_FOUND,Status.BAD_REQUEST);
        }
    }
}

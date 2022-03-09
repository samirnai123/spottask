package com.spot.task.repository;

import com.spot.task.domains.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {


    Optional<CreditCard> findCreditCardByCardNumber(String cardNumber);
}

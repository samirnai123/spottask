package com.spot.task.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "card_number")
    String cardNumber;

    @Column(name = "country")
    String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cards")
    SpotUser spotUser;

}

package com.spot.task.services.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CreditCardDTO {

    Long id;

    @NotNull
    String name;

    @NotNull
    String cardNumber;

    String country;

    Long spotUser;
}

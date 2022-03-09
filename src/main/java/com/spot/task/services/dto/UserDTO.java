package com.spot.task.services.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserDTO {

    Long id;

    String firstName;

    String lastName;

    String email;
}

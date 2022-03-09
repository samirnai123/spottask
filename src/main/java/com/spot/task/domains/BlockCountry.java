package com.spot.task.domains;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "block_country")
public class BlockCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    Long id;

    @Column(name = "country_name")
    String countryName;

    @Column(name = "country_code")
    Integer countryCode;
}

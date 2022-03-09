package com.spot.task.repository;

import com.spot.task.domains.BlockCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockCountryRepository extends JpaRepository<BlockCountry, Long> {

    Optional<BlockCountry> findBlockCountryByCountryCode(Integer countryCode);

    void deleteByCountryCode(Integer countryCode);
}

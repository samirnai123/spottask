package com.spot.task.services;

import com.spot.task.domains.BlockCountry;
import com.spot.task.services.dto.BlockCountryDTO;

public interface BlockCountryService {

    BlockCountryDTO addBlockCountry(BlockCountryDTO blockCountryDTO);

    void deleteBlockCountry(Integer countryCode);


}

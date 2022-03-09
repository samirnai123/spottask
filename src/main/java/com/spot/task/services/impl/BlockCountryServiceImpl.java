package com.spot.task.services.impl;

import com.spot.task.domains.BlockCountry;
import com.spot.task.repository.BlockCountryRepository;
import com.spot.task.resources.errors.CustomException;
import com.spot.task.resources.utils.MessageUtil;
import com.spot.task.services.BlockCountryService;
import com.spot.task.services.dto.BlockCountryDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BlockCountryServiceImpl implements BlockCountryService {

    private final Logger log = LoggerFactory.getLogger(BlockCountryServiceImpl.class);

    private final BlockCountryRepository blockCountryRepository;
    private final ModelMapper modelMapper;

    public BlockCountryServiceImpl(BlockCountryRepository blockCountryRepository, ModelMapper modelMapper) {
        this.blockCountryRepository = blockCountryRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Method for add block country
     * @param blockCountryDTO request for block country
     * @return block country dto
     */
    @Override
    public BlockCountryDTO addBlockCountry(BlockCountryDTO blockCountryDTO) {
        log.debug("request to add block country");
        BlockCountry blockCountry = modelMapper.map(blockCountryDTO, BlockCountry.class);
        BlockCountry country = blockCountryRepository.save(blockCountry);
        return modelMapper.map(country,BlockCountryDTO.class);
    }

    /**
     * Method for delete block country
     * @param countryCode country code
     */
    @Override
    public void deleteBlockCountry(Integer countryCode) {
        log.debug("Request to delete block country");
        Optional<BlockCountry> country = blockCountryRepository.findBlockCountryByCountryCode(countryCode);
        if (country.isPresent()) {
            blockCountryRepository.deleteByCountryCode(countryCode);
        }else{
            throw CustomException.throwMe(MessageUtil.ERROR,MessageUtil.ERRORS.COUNTRY_NOT_FOUND, Status.BAD_REQUEST);
        }
    }
}

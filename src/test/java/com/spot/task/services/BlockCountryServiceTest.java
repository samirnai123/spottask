package com.spot.task.services;

import com.spot.task.domains.BlockCountry;
import com.spot.task.repository.BlockCountryRepository;
import com.spot.task.resources.errors.CustomException;
import com.spot.task.services.dto.BlockCountryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class BlockCountryServiceTest {

    private final Logger log = LoggerFactory.getLogger(BlockCountryServiceTest.class);
    @MockBean
    private BlockCountryRepository blockCountryRepository;

    @Autowired
    private BlockCountryService blockCountryService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    @DisplayName("Test create country")
    void testCreate(){
        // Set up our mock repository
        BlockCountryDTO blockCountry = new BlockCountryDTO();
        blockCountry.setCountryCode(11);
        blockCountry.setCountryName("Russia");
        blockCountry.setId(1L);

        BlockCountry entity = modelMapper.map(blockCountry, BlockCountry.class);

        doReturn(entity).when(blockCountryRepository).save(any());

        //execute the service call
        BlockCountryDTO country = blockCountryService.addBlockCountry(blockCountry);

        //Assert the response
        Assertions.assertNotNull(country,"The saved country should not be null");
        Assertions.assertEquals(11,blockCountry.getCountryCode(), "Country code");
    }

    @Test
    @DisplayName("Delete block country")
    void testDelete(){
        // Set up our mock repository

        try {
            BlockCountryDTO blockCountry = new BlockCountryDTO();
            blockCountry.setCountryCode(7);
            Mockito.doNothing().when(blockCountryRepository).deleteByCountryCode(blockCountry.getCountryCode());

            blockCountryService.deleteBlockCountry(blockCountry.getCountryCode());

            //Assert the response
            Assertions.assertNotNull(blockCountry.getCountryCode(), "Country code should not be null");
        }catch (CustomException exception){
            log.debug(exception.getMessage());
        }
    }
}

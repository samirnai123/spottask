package com.spot.task.resources;


import com.spot.task.resources.utils.MessageUtil;
import com.spot.task.services.BlockCountryService;
import com.spot.task.services.dto.BlockCountryDTO;
import com.spot.task.services.generic.GenResponse;
import com.spot.task.services.generic.MyResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class BlockCountryResource {

    private final Logger log = LoggerFactory.getLogger(BlockCountryResource.class);

    private final BlockCountryService blockCountryService;

    public BlockCountryResource(BlockCountryService blockCountryService) {
        this.blockCountryService = blockCountryService;
    }

    @PostMapping("/block-country")
    public MyResponseEntity<GenResponse<BlockCountryDTO>> addBlockCountry(@Valid @RequestBody BlockCountryDTO blockCountryDTO){
        log.debug("Request to add block country");
        BlockCountryDTO blockCountry = blockCountryService.addBlockCountry(blockCountryDTO);
        return MyResponseEntity.OK(MessageUtil.SUCCESS,MessageUtil.SUCCESS_MESSAGE.COUNTRY_ADDED,blockCountry);
    }

    @DeleteMapping("/block-country/{countryCode}")
    public MyResponseEntity<GenResponse<Void>> deleteBlockCountry(@PathVariable Integer countryCode){
        log.debug("Request to delete block country");
        blockCountryService.deleteBlockCountry(countryCode);
        return MyResponseEntity.OK(MessageUtil.SUCCESS,MessageUtil.SUCCESS_MESSAGE.SUCCESSFULLY_REMOVED);
    }
}

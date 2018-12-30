package com.samsolutions.controller;

import com.samsolutions.dto.GiftTagDTO;
import com.samsolutions.service.GiftTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class GiftTagController {

    @Autowired
    private GiftTagService giftTagService;

    @GetMapping
    public List<GiftTagDTO> getAll() {
        return giftTagService.getAll();
    }

    @GetMapping(value = "/{id}")
    public GiftTagDTO get(@PathVariable Long id) {
        return giftTagService.getById(id);
    }

    @PostMapping
    public GiftTagDTO addGiftTag(@RequestBody GiftTagDTO giftTagDTO) {
        return giftTagService.add(giftTagDTO);
    }

    @PutMapping(value = "/{id}")
    public GiftTagDTO updateGiftTag(@RequestBody GiftTagDTO giftTagDTO, @PathVariable Long id) {
        return giftTagService.update(giftTagDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteGiftTag(@PathVariable Long id) {
        giftTagService.deleteById(id);
        return HttpStatus.OK;

    }
}

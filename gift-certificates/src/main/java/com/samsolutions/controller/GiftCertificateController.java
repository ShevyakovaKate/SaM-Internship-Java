package com.samsolutions.controller;

import com.samsolutions.dto.GiftCertificateDTO;
import com.samsolutions.service.GiftCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {

    @Autowired
    private GiftCertService giftCertService;


    @GetMapping
    public ModelAndView getAll(@RequestParam(value = "textpart", required = false) String textPart,
                                           @RequestParam(value = "tagname", required = false) String tagName) {

        ModelAndView modelAndView = new ModelAndView("certificates");
        if (textPart != null || tagName != null) {
            modelAndView.addObject("certificatelist", giftCertService.getByParameters(textPart, tagName));
        }
        else {
            List<GiftCertificateDTO> list =  giftCertService.getAll();
            modelAndView.addObject("certificatelist", list);

        }
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getCertificate(@PathVariable Long id) {
        //TODO смотреть кто запрашивает, и давать результат
        ModelAndView modelAndView =  new ModelAndView("single-certificate");
        modelAndView.addObject("certificate", giftCertService.getById(id));
        return modelAndView;
    }

    @PostMapping
    public GiftCertificateDTO addGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        GiftCertificateDTO result = giftCertService.add(giftCertificateDTO);
        return result;
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public GiftCertificateDTO updateGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO, @PathVariable Long id) {
        return giftCertService.update(giftCertificateDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteGiftCertificate(@PathVariable Long id) {
        giftCertService.deleteById(id);
        return HttpStatus.OK;
    }

    @DeleteMapping
    public void deleteGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        giftCertService.delete(giftCertificateDTO);
    }


}

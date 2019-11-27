package com.dementei.ec.web.controller;

import com.dementei.ec.dto.CategoryDto;
import com.dementei.ec.dto.OfferDto;
import com.dementei.ec.entity.Category;
import com.dementei.ec.entity.Offer;
import com.dementei.ec.mapper.CategoryMapper;
import com.dementei.ec.mapper.OfferMapper;
import com.dementei.ec.service.DefaultOfferService;
import com.dementei.ec.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {
    private final OfferService offerService;
    private final OfferMapper offerMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public OfferController(DefaultOfferService offerService, OfferMapper offerMapper, CategoryMapper categoryMapper) {
        this.offerService = offerService;
        this.offerMapper = offerMapper;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/saveOffer")
    public ResponseEntity<OfferDto> saveOffer(@RequestBody OfferDto offerDto) {
        Offer offer = offerMapper.toEntity(offerDto);
        Offer savedOffer = offerService.saveOffer(offer);
        return new ResponseEntity<>(offerMapper.toDto(savedOffer), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<OfferDto> findOffer(@PathVariable("id") long id) {
        Offer offer = offerService.getOfferById(id);
        return new ResponseEntity<>(offerMapper.toDto(offer), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OfferDto>> findAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        List<OfferDto> offerDtoList = offers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(offerDtoList, HttpStatus.OK);
    }

    @PostMapping("/findByCategory")
    public ResponseEntity<List<OfferDto>> findOfferByCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        List<Offer> offers = offerService.findOffersByCategory(category);
        List<OfferDto> offerDtoList = offers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(offerDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/findByPrice", params = {"min", "max"})
    public ResponseEntity<List<OfferDto>> findOfferByPrice(@RequestParam("min") double min, @RequestParam("max") double max) {
        List<Offer> offers = offerService.findOffersByPrice(min, max);
        List<OfferDto> offerDtoList = offers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(offerDtoList, HttpStatus.OK);
    }

    @PutMapping("/updateOffer")
    public ResponseEntity<OfferDto> updateOffer(@RequestBody OfferDto offerDto) {
        Offer offer = offerMapper.toEntity(offerDto);
        Offer updatedOffer = offerService.updateOffer(offer);
        return new ResponseEntity<>(offerMapper.toDto(updatedOffer), HttpStatus.OK);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<OfferDto> updateCategory(@PathVariable("id") long id, @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Offer updatedOffer = offerService.updateCategory(id, category);
        return new ResponseEntity<>(offerMapper.toDto(updatedOffer), HttpStatus.OK);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity deleteOffer(@PathVariable("id") long id) {
        offerService.deleteOfferById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

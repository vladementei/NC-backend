package com.dementei.ec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDto {
    private long id = 0;
    private String category = "";
    private Set<OfferInfoDto> offers = new HashSet<>();

    @Data
    @AllArgsConstructor
    private class OfferInfoDto{
        private long id;
        private String title;
        private String description;
        private double price;
    }
}

package com.dementei.ec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OfferDto {
    private long id = 0;
    private String title = "";
    private String description = "";
    private double price = 0.0;
    private CategoryInfoDto category = new CategoryInfoDto(0, "");

    @Data
    @AllArgsConstructor
    private class CategoryInfoDto{
        private long id;
        private String category;
    }
}

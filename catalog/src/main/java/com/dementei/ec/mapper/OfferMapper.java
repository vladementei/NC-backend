package com.dementei.ec.mapper;

import com.dementei.ec.dto.OfferDto;
import com.dementei.ec.entity.Offer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OfferMapper {
    private final ModelMapper mapper;

    @Autowired
    public OfferMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Offer toEntity(OfferDto offerDto) {
        Offer offer = Objects.isNull(offerDto) ? null : mapper.map(offerDto, Offer.class);
        offer.getCategory().getOffers().add(offer);
        offer.getPrice().setOffer(offer);
        return offer;
    }

    public OfferDto toDto(Offer entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OfferDto.class);
    }
}

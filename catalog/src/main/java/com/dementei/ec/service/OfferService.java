package com.dementei.ec.service;

import com.dementei.ec.entity.Category;
import com.dementei.ec.entity.Offer;

import java.util.List;

public interface OfferService {
    Offer saveOffer(Offer offer);

    Offer getOfferById(long id);

    List<Offer> getAllOffers();

    List<Offer> findOffersByCategory(Category category);

    List<Offer> findOffersByPrice(double minPrice, double maxPrice);

    Offer updateOffer(Offer update);

    Offer updateCategory(long id, Category update);

    void deleteOfferById(long id);
}

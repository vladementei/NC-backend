package com.dementei.ec.service;

import com.dementei.ec.entity.Category;
import com.dementei.ec.entity.Offer;
import com.dementei.ec.exception.NotFoundException;
import com.dementei.ec.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DefaultOfferService implements OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public DefaultOfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer saveOffer(Offer offer) {
        Optional<Offer> foundedOffer = this.offerRepository.findById(offer.getId());
        return foundedOffer.orElseGet(() -> this.offerRepository.save(offer));
    }

    @Override
    public Offer getOfferById(long id) {
        Optional<Offer> foundedOffer = this.offerRepository.findById(id);
        if(foundedOffer.isPresent()){
            return foundedOffer.get();
        } else {
            throw new NotFoundException("No offer with id " + id);
        }
    }

    @Override
    public List<Offer> getAllOffers() {
        return this.offerRepository.findAll();
    }

    @Override
    public List<Offer> findOffersByCategory(Category category) {
        return this.offerRepository.findByCategory(category);
    }

    @Override
    public List<Offer> findOffersByPrice(double minPrice, double maxPrice) {
        List<Offer> offers = getAllOffers();
        return offers.stream().filter(elem -> elem.getPrice().getPrice() >= minPrice && elem.getPrice().getPrice() <= maxPrice).collect(Collectors.toList());
    }

    @Override
    public Offer updateOffer(Offer update) {
        Offer foundedOffer = getOfferById(update.getId());
        foundedOffer.setTitle(update.getTitle());
        foundedOffer.setDescription(update.getDescription());
        foundedOffer.getPrice().setPrice(update.getPrice().getPrice());
        return this.offerRepository.save(foundedOffer);
    }

    @Override
    public Offer updateCategory(long id, Category update) {
        Offer foundedOffer = getOfferById(id);
        foundedOffer.setCategory(update);
        update.getOffers().add(foundedOffer);
        return this.offerRepository.save(foundedOffer);
    }

    @Override
    public void deleteOfferById(long id) {
        Offer foundedOffer = getOfferById(id);
        foundedOffer.getCategory().getOffers().remove(foundedOffer);
        this.offerRepository.deleteById(id);
    }
}

package com.dementei.ec.repository;

import com.dementei.ec.entity.Category;
import com.dementei.ec.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByCategory(Category category);
}

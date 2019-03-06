package com.heycar.heycarchallenge.domain.repository;

import com.heycar.heycarchallenge.domain.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
}

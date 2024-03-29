package com.heycar.heycarchallenge.domain.repository;


import com.heycar.heycarchallenge.domain.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
}

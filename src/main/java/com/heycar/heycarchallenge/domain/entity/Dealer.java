package com.heycar.heycarchallenge.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "DEALER")
public class Dealer {
    @Id
    private Long id;

    @OneToMany(mappedBy = "dealerId")
    private List<Listing> listings;
}

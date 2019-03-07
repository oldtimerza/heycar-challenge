package com.heycar.heycarchallenge.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "DEALER")
public class Dealer {
    @Id
    private Long id;

    @OneToMany(mappedBy = "dealerId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Listing> listings;
}

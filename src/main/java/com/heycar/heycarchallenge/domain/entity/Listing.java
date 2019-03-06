package com.heycar.heycarchallenge.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "LISTING")
public class Listing {
    @Id
    @GeneratedValue
    private Long id;
    private Long dealerId;
    private String code;
    private String make;
    private String model;
    private Long kiloWatts;
    private Long year;
    private String color;
    private Long price;

    @Override
    public boolean equals(Object o){
       return ((Listing)o).getCode() == this.getCode();
    }
}

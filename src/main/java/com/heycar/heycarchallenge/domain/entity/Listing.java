package com.heycar.heycarchallenge.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return Objects.equals(id, listing.id) &&
                Objects.equals(dealerId, listing.dealerId) &&
                Objects.equals(code, listing.code) &&
                Objects.equals(make, listing.make) &&
                Objects.equals(model, listing.model) &&
                Objects.equals(kiloWatts, listing.kiloWatts) &&
                Objects.equals(year, listing.year) &&
                Objects.equals(color, listing.color) &&
                Objects.equals(price, listing.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealerId, code);
    }

}

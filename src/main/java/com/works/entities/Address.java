package com.works.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String location;

}
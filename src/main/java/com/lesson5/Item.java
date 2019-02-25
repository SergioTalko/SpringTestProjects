package com.lesson5;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Entity
@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description")
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

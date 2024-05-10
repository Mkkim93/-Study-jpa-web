package jpabook.jpashop1.item;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("영화")
public class Movie extends Item{

    private String director; // 감독
    private String actor;    // 배우
}

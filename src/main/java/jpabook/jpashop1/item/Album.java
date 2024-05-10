package jpabook.jpashop1.item;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("음반")
public class Album extends Item{

    private String artist;  // 가수명
    private String etc;     // 기타
}

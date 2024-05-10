package jpabook.jpashop1.item;

import jakarta.persistence.DiscriminatorValue;


@DiscriminatorValue("도서")
public class Book {

    private String author; // 저자
    private String isbn;   // 도서 번호

}

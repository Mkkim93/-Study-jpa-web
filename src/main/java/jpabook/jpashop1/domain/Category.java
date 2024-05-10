package jpabook.jpashop1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jpabook.jpashop1.item.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private List<Item> items = new ArrayList<>();

    private Category parent;

    private List<Category> child = new ArrayList<Category>();
}

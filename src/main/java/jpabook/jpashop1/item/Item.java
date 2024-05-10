package jpabook.jpashop1.item;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jpabook.jpashop1.domain.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorColumn
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    private List<Category> categories = new ArrayList<>();
}

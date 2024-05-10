package jpabook.jpashop1.domain;

import jakarta.persistence.*;
import jpabook.jpashop1.item.Item;
import lombok.Getter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;
}

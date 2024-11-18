package jpabook.jpashop1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // @JsonIgnore // 주문 정보가 api 조회 시 빠진다. 즉 jsonignore 사용 시 해당 리스트 정보가 빠진다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}

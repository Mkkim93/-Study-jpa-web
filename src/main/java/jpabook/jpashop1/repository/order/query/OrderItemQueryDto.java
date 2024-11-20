package jpabook.jpashop1.repository.order.query;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderItemQueryDto {

    @JsonIgnore // 데이터를 조회하고 조건에 사용하지만 사용자 뷰페이지에는 노출(무시됨) 시키지 않는다 즉, json 파싱을 하지 않음 (json 직렬화 과정에서 생략된다)
    private Long orderId;

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemQueryDto(Long orderId, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}

package jpabook.jpashop1.controller;

import jpabook.jpashop1.domain.Member;
import jpabook.jpashop1.domain.Order;
import jpabook.jpashop1.domain.item.Item;
import jpabook.jpashop1.repository.OrderSearch;
import jpabook.jpashop1.service.ItemService;
import jpabook.jpashop1.service.MemberService;
import jpabook.jpashop1.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping(value = "/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers(); // 웹에서 만든 모든 멤버를 생성
        List<Item> items = itemService.findItems(); // 웹에서 주문한 모든 아이템을 생성

        model.addAttribute("members", members); // 생성한 멤버를 모델에 담아서 return orderForm 에 넘김
        model.addAttribute("items", items); // 생성한 상품을 모델에 담아서 return orderForm 에 넘김

        return "order/orderForm";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}

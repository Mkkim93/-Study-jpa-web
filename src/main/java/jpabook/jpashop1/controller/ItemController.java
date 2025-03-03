package jpabook.jpashop1.controller;

import jpabook.jpashop1.domain.item.Book;
import jpabook.jpashop1.domain.item.Item;
import jpabook.jpashop1.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    private String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(BookForm form) {

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /**
     * 상품 수정
     */
    @GetMapping(value = "items/{itemId}/edit") // 수정할 데이터 {} 로 지정
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) { // itemId 를 파라미터벨류로 설정 + 객체 생성
        Book item = (Book) itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping(value = "items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form) {

//        Book book = new Book();
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//
//        itemService.saveItem(book);

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}

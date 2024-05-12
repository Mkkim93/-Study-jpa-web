package jpabook.jpashop1.web;

import jakarta.validation.Valid;
import jpabook.jpashop1.domain.Address;
import jpabook.jpashop1.domain.Member;
import jpabook.jpashop1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    /**
     *  회원 가입 입력 페이지
     *  @Valid : MemberForm 에 있는 '회원 이름은 필수 입니다' 를 사용
     *  BindingResult : MemberForm 에 있는 @NotEmpty 의 message 를 hasErrors 발생 시, result 에 담는다. 그리모 massage 를 출력 한다.
     */

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // key + list
        return "members/memberList";
    }

}

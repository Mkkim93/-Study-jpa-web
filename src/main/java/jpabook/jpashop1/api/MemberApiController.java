package jpabook.jpashop1.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop1.domain.Member;
import jpabook.jpashop1.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // @Controller + @ResponseBody
@RequiredArgsConstructor
public class MemberApiController {

    private MemberService memberService;

    // 회원 조회 시 모든 회원 정보가 모두 노출이 된다.
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    // api get 호출 시, 각 m.getName() 즉, 이름을 배열로 담는다.
    @GetMapping("/api/v2/members")
    public Result memberV2() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream() // List<Member> -> List<MemberDto> 로 변경
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    // Api code V1 : api를 통해 DB 에 데이터 전송 (Entity Member 내부에서 String 변수명을 변경해도 체크를 못한다)
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) { // @Valid : JSON 데이터를 Member 로 바꿔줌
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    /**
     * Api 설계는 아래 code2 와 같이 Entity(Member) 의 객체를 절대로 노출 시키지 않게 설계 해야 한다. (별도의 DTO 를 받는 방식)
     */
    // Api code V2 : api를 통해 DB 로 데이터 전송 (Entity Member 내부에서 String name 값을 username 으로 변경하면 오류(검증) 발생 시킨다.
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // update (수정)
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data // V2를 위한 별도의 DTO 클래스 생성
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}

package jpabook.jpashop1.service;

import jpabook.jpashop1.domain.Member;
import jpabook.jpashop1.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기 전용으로 설정, public Method 에 기본적으로 트랜잭션이 적용 (Spring framework 가 제공하는 애노테이션을 사용하자!)
@RequiredArgsConstructor // final 이 있는 객체만 생성자를 생성한다.
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * func1 : 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복회원 차단 logic
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());// memberRepository 에서 회원이름을 조회해서 같은 이름이 있는지 확인
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    /**
     * func2 : 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

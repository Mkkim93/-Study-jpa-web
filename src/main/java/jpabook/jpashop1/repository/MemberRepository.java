package jpabook.jpashop1.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop1.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Spring Bean 에 등록
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 회원 등록
    public void save(Member member) {
        em.persist(member);
    }

    // 회원 조회 (아이디)
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 회원 목록 조회
    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    // 회원 조회 (이름)
    public List<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

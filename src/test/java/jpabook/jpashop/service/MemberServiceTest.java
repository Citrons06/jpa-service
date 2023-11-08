package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.OldMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  //테스트에선 커밋하지 않고 롤백, 영속성 컨텍스트가 플러시X
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    OldMemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(value = false)  // 롤백하지 않고 DB에 반영
    void join() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        em.flush(); // DB에 날리는 쿼리 확인
        assertEquals(member, memberService.findOne(savedId));
    }

    @Test()
    void validationDuplicate() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);

        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }
}
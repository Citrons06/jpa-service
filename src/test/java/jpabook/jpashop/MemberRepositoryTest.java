package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @Rollback(value = false)
    @Test
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setId(member.getId());

        // when
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findOne(member.getId());

        // then
        Assertions.assertThat(savedMember).isEqualTo(findMember);
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

        // 영속성 컨텍스트에서 식별자가 같으면 같은 엔티티로 인식한다. JPA 엔티티 동일성 보장!
        System.out.println("findMember == member: " + (findMember == member));
    }
}
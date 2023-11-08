package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OldMemberRepository {

    /**
     * Spring이 엔티티매니저를 엔티티에 주입
     */
    private EntityManager em;

    /*
      엔티티매니저 팩토리 주입
      @PersistenceUnit
      private EntityManagerFactory emf;
     */

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

// jpa를 사용하려면 트렌젝션이 있어야 한다 -> 모든 데이터 변경이 일어나는 데이터 저장, 변경은 모두 트렌젝션 안
@Transactional // 회원가입할때만 필요하므로 회원가입 위 (save)에만 설정해주어도 됨
public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em; // db 통신 처리
    // jpa를 사용하려면 EntityManager을 불러와야 함
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member); // 영구 저장
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);// 조회할 때는 find
        return Optional.ofNullable(member);
    }
    // pk 기반이 아니라면 직접 작성해주어야 함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // * 이 아니라 객체 m 자체를 찾음
    }
}

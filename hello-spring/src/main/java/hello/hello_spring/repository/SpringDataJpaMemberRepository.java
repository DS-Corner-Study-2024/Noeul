package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// SpringDataJpa가 전체를 빈으로 만들어 등록해줌
@Primary
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository  {
    @Override
    Optional<Member> findByName(String name); // 구현하지 않아도 됨
}

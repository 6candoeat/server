package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String id);
    Optional<Member> findByLoginIdAndPassword(String loginId, String password);
}

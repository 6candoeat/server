package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

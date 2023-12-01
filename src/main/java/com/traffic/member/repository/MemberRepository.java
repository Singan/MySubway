package com.traffic.member.repository;

import com.traffic.member.dto.req.SignupReqDto;
import com.traffic.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByMemberEmail(String email);
    boolean existsByMemberEmail(String email);
    Member findMemberByMemberEmail(String email);

}

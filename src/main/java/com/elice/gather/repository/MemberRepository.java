package com.elice.gather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elice.gather.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByUserId(String userId);

}

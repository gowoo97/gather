package com.elice.gather.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.elice.gather.entity.Member;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	
	@Test
	void findByUserIdTest() {
		Member member = Member.builder().userId("tom").password("1234").build();
		memberRepository.save(member);
		Member foundMember = memberRepository.findByUserId("tom");
		assertEquals(foundMember.getUserId(),"tom");
	}

}

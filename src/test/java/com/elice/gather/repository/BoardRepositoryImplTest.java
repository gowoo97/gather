package com.elice.gather.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.elice.gather.DTO.BoardDTO;
import com.elice.gather.entity.Member;
import com.elice.gather.repository.interfaces.BoardRepository;



@SpringBootTest
class BoardRepositoryImplTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	void 게시판_생성() {
	Member member = Member.builder().userId("tom").password("1234").build();
	memberRepository.save(member);
	
	BoardDTO board = BoardDTO.builder().publisher(member.getId()).boardName("등산").build();
	BoardDTO savedBoard = boardRepository.save(board);

	assertEquals(board.getBoardName(), savedBoard.getBoardName());
	
	}


}

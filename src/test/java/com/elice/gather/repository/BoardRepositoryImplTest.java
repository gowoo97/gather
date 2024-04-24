package com.elice.gather.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.elice.gather.entity.Board;
import com.elice.gather.repository.interfaces.BoardRepository;

@SpringBootTest
class BoardRepositoryImplTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	void 게시판_생성() {
	Board board = new Board();
	board.setBoardName("등산");
	Board savedBoard = boardRepository.save(board);

	 assertEquals(board.getBoardName(), savedBoard.getBoardName());
	



	}


}

package com.elice.gather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elice.gather.entity.Board;
import com.elice.gather.entity.Member;
import com.elice.gather.repository.JpaBoardRepository;
import com.elice.gather.repository.MemberRepository;

@Service
public class JpaBoardServiceImpl {

	@Autowired
	private JpaBoardRepository boardRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Transactional
	public Board save(String boardName, String userId) {
		Member publisher = memberRepository.findByUserId(userId);
		Board board = Board.builder().boardName(boardName).publisher(publisher).build();
		return boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public List<Board> getBoards(int cnt) {
		return boardRepository.findAll(PageRequest.of(0,10)).toList();
	}


	@Transactional(readOnly = true)
	public Board getBoardById(long id) {
		return boardRepository.findById(id).get();
	}

	
	@Transactional
	public int modifyBoard(long boardId, String boardName, String userId) {
		Board board = boardRepository.findById(boardId).get();
		
		if(!board.getPublisher().getUserId().equals(userId)) {
			return -1;
		}
		
		board.setBoardName(boardName);
		
		return 1;
	}

	@Transactional
	public int deleteBoard(long id, String userId) {
		Board board = boardRepository.findById(id).get();
		
		if(!board.getPublisher().getUserId().equals(userId)) {
			return -1;
		}
		
		boardRepository.delete(board);
		
		return 1;
	}

}

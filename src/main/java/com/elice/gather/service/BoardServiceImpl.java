package com.elice.gather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elice.gather.DTO.BoardDTO;
import com.elice.gather.entity.Member;
import com.elice.gather.repository.MemberRepository;
import com.elice.gather.repository.interfaces.BoardRepository;
import com.elice.gather.service.interfaces.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MemberRepository memberRepository;


	@Override
	@Transactional
	public BoardDTO save(String boardName,String userId) {
		Member publisher = memberRepository.findByUserId(userId);
		BoardDTO newBoard = BoardDTO.builder().boardName(boardName).publisher(publisher.getId()).build();
		return boardRepository.save(newBoard);
	}

	@Override
	public List<BoardDTO> getBoards(int cnt) {
		return boardRepository.getBoards(cnt);
	}

	@Override
	public BoardDTO getBoardById(long id) {
	
		return boardRepository.getBoardById(id);
	}

	@Override
	@Transactional
	public int modifyBoard(BoardDTO board) {
		return boardRepository.modifyBoard(board);
	}

	@Override
	@Transactional
	public void deleteBoard(long id) {
		boardRepository.deleteBoard(id);
	}
	
	
	
}

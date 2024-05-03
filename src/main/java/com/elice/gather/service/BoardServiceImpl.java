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
	public int modifyBoard(long boardId,String boardName,String userId) {
		BoardDTO beforeBoard = boardRepository.getBoardById(boardId);
		Member publisher = memberRepository.findById(beforeBoard.getPublisher()).get();
		
		if(!userId.equals(publisher.getUserId())) {
			return -1;
		}
		
		
		beforeBoard.setBoardName(boardName);
		
		
		return boardRepository.modifyBoard(beforeBoard);
	}

	@Override
	@Transactional
	public int deleteBoard(long id,String userId) {
		BoardDTO boardDTO= boardRepository.getBoardById(id);
		Member member = memberRepository.findByUserId(userId);
		
		if(boardDTO.getPublisher() != member.getId()) {
			return -1;
		}
		
		return  boardRepository.deleteBoard(id);
	}
	
	
	
}

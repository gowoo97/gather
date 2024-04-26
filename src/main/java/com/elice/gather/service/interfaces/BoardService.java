package com.elice.gather.service.interfaces;

import java.util.List;

import com.elice.gather.DTO.BoardDTO;

public interface BoardService {
	
	
	BoardDTO save(String boardName,String userId);
	
	List<BoardDTO> getBoards(int cnt);
	
	BoardDTO getBoardById(long id);
	
	int modifyBoard(BoardDTO board);
	
	void deleteBoard(long id);
}

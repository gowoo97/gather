package com.elice.gather.service.interfaces;

import java.util.List;

import com.elice.gather.DTO.BoardDTO;

public interface BoardService {
	
	
	BoardDTO save(String boardName,String userId);
	
	List<BoardDTO> getBoards(int cnt);
	
	BoardDTO getBoardById(long id);
	
	int modifyBoard(long boardId,String boardName,String userId);
	
	int deleteBoard(long id,String userId);
}

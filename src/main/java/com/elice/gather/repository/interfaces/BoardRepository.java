package com.elice.gather.repository.interfaces;

import java.util.List;

import com.elice.gather.DTO.BoardDTO;
import com.elice.gather.entity.Board;

public interface BoardRepository {

	
	BoardDTO save(BoardDTO board);
	
	List<BoardDTO> getBoards(int cnt);
	
	BoardDTO getBoardById(long id);
	
	int modifyBoard(BoardDTO board);
	
	void deleteBoard(long id);
	
}

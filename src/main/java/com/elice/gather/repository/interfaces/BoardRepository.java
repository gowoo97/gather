package com.elice.gather.repository.interfaces;

import java.util.List;

import com.elice.gather.entity.Board;

public interface BoardRepository {

	
	Board save(Board board);
	
	List<Board> getBoards(int cnt);
	
	
}

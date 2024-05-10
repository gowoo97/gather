package com.elice.gather.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.elice.gather.entity.Board;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {

	
	Page<Board> findByBoardNameContaining(String boardName, Pageable pageable);
	
}

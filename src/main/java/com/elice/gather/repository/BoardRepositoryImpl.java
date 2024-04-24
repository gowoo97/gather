package com.elice.gather.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.elice.gather.entity.Board;
import com.elice.gather.repository.interfaces.BoardRepository;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

	private final JdbcTemplate jdbcTemplate;

    public BoardRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public Board save(Board board) {
		jdbcTemplate.update("insert into board (board_name) values(?)",board.getBoardName());
		return board;
	}

	@Override
	public List<Board> getBoards(int cnt) {
		List<Board> boards = jdbcTemplate.query("select * from board limit "+cnt, boardRowMapper());
		return boards;
	}
	
	
	 private RowMapper<Board> boardRowMapper(){
	        return (rs, rowNum) -> {
	              Board board = new Board();
	              board.setId(rs.getLong("id"));
	              board.setBoardName(rs.getString("board_name"));
	            return board;
	        };
	    }
	
	
}

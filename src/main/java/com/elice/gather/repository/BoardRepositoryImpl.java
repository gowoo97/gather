package com.elice.gather.repository;

import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.elice.gather.DTO.BoardDTO;
import com.elice.gather.entity.Board;
import com.elice.gather.repository.interfaces.BoardRepository;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

	private final JdbcTemplate jdbcTemplate;

    public BoardRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public BoardDTO save(BoardDTO board) {
		String sql = "insert into board (publisher_id,board_name) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection ->{
			PreparedStatement preparedStatement = connection.prepareStatement(sql,new String[] {"id"});
			preparedStatement.setLong(1,board.getPublisher());
			preparedStatement.setString(2,board.getBoardName());
			return preparedStatement;
		}, keyHolder);
		
		System.out.println(keyHolder.getKey().longValue());
		board.setId(keyHolder.getKey().longValue());
		return board;
	}

	@Override
	public List<BoardDTO> getBoards(int cnt) {
		List<BoardDTO> boards = jdbcTemplate.query("select * from board limit "+cnt, boardRowMapper());
		return boards;
	}
	
	@Override
	public BoardDTO getBoardById(long id) {
		String sql = "select * from board where id = ?";
		return jdbcTemplate.queryForObject(sql,boardRowMapper(),id);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		String sql = "update board set board_name = ? where id = ?";
		return jdbcTemplate.update(sql,board.getBoardName(),board.getId());
	}

	@Override
	public void deleteBoard(long id) {
		String sql = "delete from board where id = "+id;
		jdbcTemplate.update(sql);
	}
	
	
	
	 private RowMapper<BoardDTO> boardRowMapper(){
	        return (rs, rowNum) -> {
	              BoardDTO board = new BoardDTO();
	              board.setId(rs.getLong("id"));
	              board.setPublisher(rs.getLong("publisher_id"));
	              board.setBoardName(rs.getString("board_name"));
	            return board;
	        };
	    }

	
	
	
}

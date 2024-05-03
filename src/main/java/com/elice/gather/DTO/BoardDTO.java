package com.elice.gather.DTO;

import com.elice.gather.entity.Board;
import com.elice.gather.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	
	private long id;
	
	private long publisher;
	
	private String boardName;
	
	
	public Board toEntity(Member publisher) {
			return Board.builder()
					.id(this.id)
					.publisher(publisher)
					.boardName(this.boardName).build();
	}
}

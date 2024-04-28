package com.elice.gather.DTO;

import com.elice.gather.entity.Board;
import com.elice.gather.entity.Member;
import com.elice.gather.entity.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {


	private long id;

	private long board_id;
	
	private String publisher;
	
	private String title;
	
	private String content;
	
	private String dayOfWeek;
	
	private int participants;
	
	private String imagePath;
	
	private int maxParticipants;
	
	
	public Post toEntity(Board board,Member publisher) {
		return Post.builder()
				.id(id)
				.board(board)
				.publisher(publisher)
				.title(title)
				.content(content)
				.dayOfWeek(dayOfWeek)
				.participants(participants)
				.imagePath(imagePath)
				.maxParticipants(maxParticipants)
				.build();
	}
	
}

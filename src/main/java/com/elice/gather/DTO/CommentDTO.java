package com.elice.gather.DTO;

import com.elice.gather.entity.Comment;
import com.elice.gather.entity.Member;
import com.elice.gather.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

	private long id;
	
	private long post;
	
	private String publisher;
	
	private String content;
	
	
	public Comment toEntity(Post post,Member publisher) {
		return Comment.builder()
				.id(this.id)
				.post(post)
				.publisher(publisher)
				.content(this.content)
				.build();
	}
	
}

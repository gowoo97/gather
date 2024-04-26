package com.elice.gather.DTO;

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
	
}

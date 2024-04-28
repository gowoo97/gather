package com.elice.gather.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Board board;
	
	@ManyToOne
	private Member publisher;
	
	private String title;
	
	private String content;
	
	private String dayOfWeek;
	
	private int participants;
	
	private String imagePath;
	
	private int maxParticipants;
	
	
	
}

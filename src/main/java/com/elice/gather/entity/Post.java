package com.elice.gather.entity;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Member publisher;
	
	private String title;
	
	private String content;
	
	private String dayOfWeek;
	
	@OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
	private List<Request> participants;
	
	private String imagePath;
	
	private int maxParticipants;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;

	
}

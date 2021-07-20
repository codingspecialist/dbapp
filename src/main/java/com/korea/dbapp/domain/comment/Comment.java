package com.korea.dbapp.domain.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.user.User;

import lombok.Data;

@Data // Getter, Setter, ToString 을 만들어준다.
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 프라이머리키 (기본키)
	private String text;
	
	@JsonIgnoreProperties({"posts"})
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	
	@JsonIgnoreProperties({"user"})
	@JoinColumn(name = "post_id")
	@ManyToOne
	private Post post;
}




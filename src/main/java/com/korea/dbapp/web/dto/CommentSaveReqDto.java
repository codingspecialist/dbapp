package com.korea.dbapp.web.dto;

import lombok.Data;

// 1
@Data
public class CommentSaveReqDto {
	private String text;
	private int postId;
}

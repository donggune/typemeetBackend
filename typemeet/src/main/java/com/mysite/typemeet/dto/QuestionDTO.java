package com.mysite.typemeet.dto;

import com.mysite.typemeet.model.QuestionEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDTO {
	private String id;
	private String questionItem1;
	private String questionItem2;
	
	public QuestionDTO(final QuestionEntity entity) {
		this.id = entity.getId();
		this.questionItem1 = entity.getQuestionItem1();
		this.questionItem2 = entity.getQuestionItem2();
	}
	
	public static QuestionEntity toEntity(final QuestionDTO dto) {
		return QuestionEntity.builder()
				.id(dto.getId())
				.questionItem1(dto.getQuestionItem1())
				.questionItem2(dto.getQuestionItem2())
				.build();
	}
}

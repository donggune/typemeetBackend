package com.mysite.typemeet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.typemeet.model.QuestionEntity;
import com.mysite.typemeet.persistence.QuestionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TypeService {

	@Autowired
	private QuestionRepository repository;
	
	/**
	 * 설문지 항목 생성
	 * */
	public List<QuestionEntity> create(final QuestionEntity entity){
		
		// Validations
		validate(entity);
		
		repository.save(entity);
		
		log.info("잘 저장되었습니다. Entity ID {}", entity.getId());
		
		return retrieve();
	}
	
	/**
	 * 설문지 항목 조회
	 * */
	public List<QuestionEntity> retrieve(){
		return repository.findAll();
	}
	
	
	/**
	 * 설문지 항목 수정
	 * */
	public List<QuestionEntity> update(final QuestionEntity entity){
		
		validate(entity);
		
		Optional<QuestionEntity> optional = repository.findById(entity.getId());
		
		optional.ifPresent( question -> {
			question.setQuestionItem1(entity.getQuestionItem1());
			question.setQuestionItem2(entity.getQuestionItem2());
			
			repository.save(question);
		});
		
		return retrieve();
	}
	
	/**
	 * 설문지 항목 삭제
	 * */
	public List<QuestionEntity> delete(final QuestionEntity entity){
		
		validate(entity);
		
		try {
			repository.delete(entity);
			
		} catch (Exception e) {
			log.error("삭제에 실패하였읍니다.", entity.getId(), e);
			throw new RuntimeException("삭제에 실패하였읍니다."+ entity.getId());
		}
		
		return retrieve();
	}
	
	private void validate(final QuestionEntity entity) {
		
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot bt null");
		}
		
		if(entity.getRegId() == null) {
			log.warn("Unkown user");
			throw new RuntimeException("Unkown user");
		}
	}
}

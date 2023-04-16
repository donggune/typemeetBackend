package com.mysite.typemeet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.typemeet.dto.QuestionDTO;
import com.mysite.typemeet.dto.ResponseDTO;
import com.mysite.typemeet.model.QuestionEntity;
import com.mysite.typemeet.service.TypeService;

@RestController
@RequestMapping("type")
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	@PostMapping
	public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO dto){
		
		try {
			String tempAdmin = "tempAdmin"; // 임시 admin
			
			QuestionEntity entity = QuestionDTO.toEntity(dto);
			
			
			entity.setId(null);
			
			entity.setRegId(tempAdmin);
			
			List<QuestionEntity> entities = typeService.create(entity);
			
			List<QuestionDTO> dtos = entities.stream().map(QuestionDTO::new).collect(Collectors.toList());
			
			ResponseDTO<QuestionDTO> response = ResponseDTO.<QuestionDTO>builder().data(dtos).build();
					
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<QuestionDTO> response = ResponseDTO.<QuestionDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retreiveQuestionList(){
		
		String tempAdmin = "tempAdmin";
	
		List<QuestionEntity> entities = typeService.retrieve();
		
		List<QuestionDTO> dtos = entities.stream().map(QuestionDTO::new).collect(Collectors.toList());
		
		ResponseDTO<QuestionDTO> response = ResponseDTO.<QuestionDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
			
	}
	
	@PutMapping
	public ResponseEntity<?> updateQuestion(@RequestBody QuestionDTO dto){
		String tempAdmin = "tempAdmin";
		
		QuestionEntity entity = QuestionDTO.toEntity(dto);
		
		entity.setRegId(tempAdmin);
		
		List<QuestionEntity> entities = typeService.update(entity);
		
		List<QuestionDTO> dtos = entities.stream().map(QuestionDTO::new).collect(Collectors.toList());
		
		ResponseDTO<QuestionDTO> response = ResponseDTO.<QuestionDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteQuestion(@RequestBody QuestionDTO dto){
		
		try {
			String tempAdmin = "tempAdmin";
			
			QuestionEntity entity = QuestionDTO.toEntity(dto);
			
			entity.setRegId(tempAdmin);
			
			List<QuestionEntity> entities = typeService.delete(entity);
			
			List<QuestionDTO> dtos = entities.stream().map(QuestionDTO::new).collect(Collectors.toList());
			
			ResponseDTO<QuestionDTO> response = ResponseDTO.<QuestionDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<QuestionDTO> response = ResponseDTO.<QuestionDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
		
		
	}
}

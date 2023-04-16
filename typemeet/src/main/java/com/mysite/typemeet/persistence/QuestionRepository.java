package com.mysite.typemeet.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysite.typemeet.model.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, String>{

}

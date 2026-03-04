package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    //JpaRepository는 JPA가 제공하는 인터페이스 중 하나로 CRUD 작업을 처리하는 메서드들을 이미 내장하고 있어 데이터 관리 작업을 좀 더 편리하게 처리

    /***
     * 구현하지 않았는데 어떻게 조회 되는지는 로그를 통해 확인
     *  - spring.jpa.properties.hibernate.format_sql=true
     *  - spring.jpa.properties.hibernate.show_sql=true
     * @param subject
     * @return Question
     */
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);
}

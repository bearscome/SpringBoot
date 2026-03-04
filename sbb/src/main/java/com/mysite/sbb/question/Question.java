package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import java.time.LocalDateTime;

@Getter
@Setter // @Entity 어노테이션이 있는 경우, setter은 쓰지말자(현재 학습 용도로 사용하고 있으나, 실무에서는 따로 엔티티를 만들어서 사용)
@Entity // 엔티티로 인식
public class Question {
    @Id // id 속성을 적용하여 id 속성을 기본 키로 지정(고유 값)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 값을 일일히 증감하지 않고 자동으로 증감하도록 어노테이션 추가
    private Integer id;

    @Column(length = 100)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // 해당 질문이 삭제되면 맵핑되어 있는 답변도 삭제되도록(CascadeType.REMOVE)
    private List<Answer> answerList;
}

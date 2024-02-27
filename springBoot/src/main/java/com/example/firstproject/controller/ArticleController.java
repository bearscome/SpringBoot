package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 골뱅이(이노테이션)
public class ArticleController {
    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form.toString()); -> printLn은 서버에서 사용 안함 -> 성능 저하 -> 로깅으로 대체한다.

        // Form에서 전달된 데이터를 DB로 저장
        // 1. DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(form.toString());
//        System.out.println(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

//        System.out.println(saved);


        return "";
    }
}

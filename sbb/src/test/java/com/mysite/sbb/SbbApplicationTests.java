package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("sbb1");
		q1.setContent("sbb1");
		//ㅇㅇ
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("sbb2");
		q2.setContent("sbb2");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}

	@Test
	void testFindAll() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb1", q.getSubject());
	}

	@Test
	void testFindById() {
		Optional<Question> oq = this.questionRepository.findById(2);
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb2", q.getSubject());
		}
	}

	@Test
	void testFindBySubject() {
		Question q = this.questionRepository.findBySubject("sbb1");

		if(q != null) {
			System.out.printf("q = " + q.getSubject());
			assertEquals(1, q.getId());
		}
		else {
			System.out.println("데이터가 없습니다. = " + q);
		}
	}

	@Test
	void TestFindBySubjectAndContent() {
		Question q = this.questionRepository.findBySubjectAndContent("sbb1", "sbb1");

		if(q != null) {
			System.out.println("q = " + q);
			assertEquals(1, q.getId());
		}
		else {
			System.out.println("q is null = " + q);
		}
	}

	@Test
	void TestFindBySubjectLike() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb1%");
		Question q = qList.get(0);
		assertEquals("sbb1", q.getSubject());
	}

	@Test
	void setQuestionSubject() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent()); // true인지 확인
		Question q = oq.get();
		q.setSubject("수정을 더 한 제목");
		this.questionRepository.save(q);

	}

}

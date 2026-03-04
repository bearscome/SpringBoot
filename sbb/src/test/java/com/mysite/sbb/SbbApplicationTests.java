package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("sbb1");
		q1.setContent("sbb1");
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

	@Test
	void deleteQuestion() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}

	@Test
	void saveAnswer() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성 됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}

	@Test
	void getFindById() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}

	@Transactional
	@Test
	void getAnswerByQuestion() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성 됩니다.", answerList.get(0).getContent());
	}
}

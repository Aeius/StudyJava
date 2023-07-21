package com.gradleJSP.demo;


import com.gradleJSP.demo.entity.People;
import com.gradleJSP.demo.service.PeopleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.StringTokenizer;

@SpringBootTest
@Transactional
class DemoApplicationTests {

	@Autowired
	private PeopleService peopleService;

	@Test
	@DisplayName("사람 조회")
	@Order(1)
	void test_selectAll() {
		Assertions.assertFalse(peopleService.selectAll().isEmpty());
	}
	@Test
	@DisplayName("사람 추가")
	@Order(2)
	void test_insert() {
		People p = peopleService.insert(new People(0, "테스트", 10));
		Assertions.assertEquals("테스트", p.getName());
		Assertions.assertEquals(10, p.getAge());
	}

	@Test
	@DisplayName("사람 수정")
	@Order(3)
	void test_update() {
		peopleService.update(2L, new People(0, "김수정", 25));
		People p = peopleService.selectById(2L).get();
		Assertions.assertEquals("김수정", p.getName());
		Assertions.assertEquals(25, p.getAge());
	}

	@Test
	@DisplayName("사람 삭제")
	@Order(4)
	void test_delete() {
		peopleService.delete(2L);
		Assertions.assertFalse(peopleService.selectById(2L).isPresent());
	}

}

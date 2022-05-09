package com.hydroyura.TechDocsManager.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.BaseRepositoryWithSpecification;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.Specification.PartSpecification;

@DataJpaTest
public class PartRepositoryWithSpecificationTest {

	@Autowired @Qualifier(value = "PartRepositoryWithSpecification")
	private BaseRepositoryWithSpecification<PartEntity> repository;
	
	@BeforeEach
	void fillDataBase() {
		repository.save(new PartEntity(0, "NAME_10", "NUMBER_10", null, null, "Серия"));
		repository.save(new PartEntity(0, "NAME_10", "NUMBER_11", null, null, "Серия"));
		repository.save(new PartEntity(0, "NAME_01", "NUMBER_02", null, null, "Задание"));
		repository.save(new PartEntity(0, "NAME_01", "NUMBER_03", null, null, "Задание"));
		repository.save(new PartEntity(0, "NAME_02", "NUMBER_04", null, null, "Проектирование"));
	}
	
	@Test
	void autowiredTest() {
		assertNotNull(repository);
	}
	

	@Test
	void test1_NameLikeAndNumberLike() {
		Map<String, String> params = new HashMap<>();
		params.put("NUMBER", "NUMBER");
		params.put("NAME", "10");
		assertEquals(2, repository.findAll(new PartSpecification(params).getSpecification()).size());
	}
	
	@Test
	void test2_NameLikeAndNumberLike() {
		Map<String, String> params = new HashMap<>();
		params.put("NUMBER", "NUMBER_0");
		params.put("NAME", "");
		assertEquals(3, repository.findAll(new PartSpecification(params).getSpecification()).size());
	}
	
	@Test
	void test3_NameLikeAndNumberLike() {
		Map<String, String> params = new HashMap<>();
		params.put("NUMBER", "11");
		params.put("NAME", "0");
		assertEquals(1, repository.findAll(new PartSpecification(params).getSpecification()).size());
	}	
	
	@Test
	void test4_NameLikeAndNumberLike() {
		Map<String, String> params = new HashMap<>();
		params.put("NUMBER", "R_0");
		params.put("NAME", "_0");
		List<PartEntity> items = repository.findAll(new PartSpecification(params).getSpecification());
		items.forEach(System.out::println);
		assertEquals(3, items.size());
	}
	
	@Test
	void test5_NameLikeAndNumberLike() {
		Map<String, String> params = new HashMap<>();
		assertEquals(5, repository.findAll(new PartSpecification(params).getSpecification()).size());
	}
	
}

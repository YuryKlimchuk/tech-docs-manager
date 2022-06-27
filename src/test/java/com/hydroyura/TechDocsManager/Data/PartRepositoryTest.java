package com.hydroyura.TechDocsManager.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;

@DataJpaTest
public class PartRepositoryTest {

	@Autowired @Qualifier(value = "PartRepository")
	private JpaRepository<PartEntity, Long> partRepository;
	
	@BeforeEach
	void fillTable() {
		partRepository.save(new PartEntity(0, "PART_NAME_00", "PART_NUMBER_00", null, null, "STATUS_00"));
		partRepository.save(new PartEntity(0, "PART_NAME_00", "PART_NUMBER_01", null, null, "STATUS_00"));
		partRepository.save(new PartEntity(0, "PART_NAME_02", "PART_NUMBER_02", null, null, "STATUS_01"));
		partRepository.save(new PartEntity(0, "PART_NAME_02", "PART_NUMBER_03", null, null, "STATUS_02"));
		partRepository.save(new PartEntity(0, "PART_NAME_04", "PART_NUMBER_04", null, null, "STATUS_02"));
	}
	
	@Test
	void autowiredTest() throws Exception{
		assertNotNull(partRepository);
		assertEquals(5, partRepository.findAll().size());
	}
	
	@Test
	void updateTest() {
		PartEntity entity = partRepository.save(new PartEntity(0, "PART_NAME_04", "PART_NUMBER_05", null, null, "STATUS_02"));
		long id = entity.getId();
		entity.setNumber("PART_NUMBER_06");
		PartEntity updateEntity = partRepository.save(entity);
		assertEquals(id, updateEntity.getId());
	}
	
	
	
}

package com.hydroyura.TechDocsManager.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Service.AbstractSpecificationElementService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PartServiceIntegratingTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	private AbstractSpecificationElementService<PartDTO, PartEntity> partService;
	
	
	
	@Test
	void autowiredTest() {
		assertNotNull(partService);
	}
	
	@Test
	void saveTest() throws Exception {
		PartDTO part1 = new PartDTO(0, "НОМЕР_Деталь_01", "ИМЯ_Деталь_01", "Тестовый_Статус");
		Optional<PartDTO> part1New = partService.save(part1);
		assertEquals(part1.getNumber(), part1New.get().getNumber());
		assertEquals(part1.getName(), part1New.get().getName());
		assertEquals(part1.getStatus(), part1New.get().getStatus());
		assertNotEquals(part1.getId(), part1New.get().getId());
		
		
		PartDTO part2 = new PartDTO();
		Optional<PartDTO> part2New = partService.save(part2);
		assertTrue(part2New.isEmpty());
	}
	
	@Test
	void getByIdTest() {
		Optional<PartDTO> part1New = partService.save(new PartDTO(0, "НОМЕР_Деталь_01", "ИМЯ_Деталь_01", "Тестовый_Статус"));
		assertEquals(part1New.get().getNumber(), partService.getById(part1New.get().getId()).get().getNumber());
	}
	
	@Test
	void deleteTest() {
		long count = StreamSupport.stream(partService.getAll().spliterator(), false).count();
		Optional<PartDTO> part1New = partService.save(new PartDTO(0, "НОМЕР_Деталь_01", "ИМЯ_Деталь_01", "Тестовый_Статус"));
		partService.deleteById(part1New.get().getId());
		assertEquals(count, StreamSupport.stream(partService.getAll().spliterator(), false).count());
	}
	
	@Test
	void getAllTest() {
		long count = StreamSupport.stream(partService.getAll().spliterator(), false).count();
		partService.save(new PartDTO(0, "НОМЕР_Деталь_01", "ИМЯ_Деталь_01", "Тестовый_Статус"));
		partService.save(new PartDTO(0, "НОМЕР_Деталь_02", "ИМЯ_Деталь_02", "Тестовый_Статус"));
		partService.save(new PartDTO(0, "НОМЕР_Деталь_03", "ИМЯ_Деталь_03", "Тестовый_Статус"));
		assertEquals(count + 3, StreamSupport.stream(partService.getAll().spliterator(), false).count());
	}
	
	
	
	
}

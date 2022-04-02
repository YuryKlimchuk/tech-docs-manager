package com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;

@Component(value = "AssemblyConverter")
public class AssemblyConverter implements IConverter<AssemblyEntity, AssemblyDTO> {
	
	@Override
	public AssemblyEntity convertFromDtoToEntity(AssemblyDTO dto) {
		
		AssemblyEntity entity = new AssemblyEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
		entity.setUpdate(dto.getUpdate());
		
		if(dto.getPdf() != null) {
			
			String fileName = "./src/main/resources/static/draw/pdf/" + dto.getNumber() + "_" + dto.getName() + ".pdf";
			fileName.replaceAll("/", "_");
			
			File file = new File(fileName);
			
			try(FileOutputStream out = new FileOutputStream(file)) {
				if(!file.exists()) file.createNewFile();
				
				out.write(dto.getPdf());
				out.flush();
				entity.setPdf(file.getPath());

			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		} else {
			entity.setPdf("N/A");
		}
		
		return entity;
	}

	@Override
	public AssemblyDTO convertFromEntityToDto(AssemblyEntity entity) {
		
		AssemblyDTO dto = new AssemblyDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());
		dto.setUpdate(entity.getUpdate());
		
		
	
		if(entity.getPdf() == null || entity.getPdf().equals("N/A")) {
			dto.setPdf(null);
		} else {
			File file = new File(entity.getPdf());
			try(InputStream in =  new FileInputStream(file)) {
				dto.setPdf(IOUtils.toByteArray(in));
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}

		
		
		return dto;
	}
}

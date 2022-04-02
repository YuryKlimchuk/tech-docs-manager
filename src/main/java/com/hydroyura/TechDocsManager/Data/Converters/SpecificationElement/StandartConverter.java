package com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;

@Component(value = "StandartConverter")
public class StandartConverter implements IConverter<StandartEntity, StandartDTO> {

	@Override
	public StandartEntity convertFromDtoToEntity(StandartDTO dto) {
		
		StandartEntity entity = new StandartEntity();
		
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		entity.setName(dto.getName());
		entity.setUpdate(dto.getUpdate());
		entity.setStandart(dto.getStandart());
		
		if(dto.getPdf() != null) {
			
			String fileName = "./src/main/resources/static/draw/pdf/" + dto.getName() + "_" + dto.getStandart() + "_" + dto.getNumber() + ".pdf";
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
	public StandartDTO convertFromEntityToDto(StandartEntity entity) {
		
		StandartDTO dto = new StandartDTO();
		
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		dto.setName(entity.getName());
		dto.setUpdate(entity.getUpdate());
		dto.setStandart(entity.getStandart());
		
		if(entity.getPdf().equals("N/A")) {
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

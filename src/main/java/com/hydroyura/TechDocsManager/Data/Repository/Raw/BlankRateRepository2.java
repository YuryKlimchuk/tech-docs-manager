package com.hydroyura.TechDocsManager.Data.Repository.Raw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydroyura.TechDocsManager.Data.Entity.Raw.BlankRateEntity;

@Repository(value = "BlankRateRepository2")
public interface BlankRateRepository2 extends JpaRepository<BlankRateEntity, Long> {

	
	List<BlankRateEntity> findByRoute_Id(long id);

}

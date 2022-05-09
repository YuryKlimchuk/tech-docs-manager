package com.hydroyura.TechDocsManager.Controller.WEB.Raw.Material;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractImpl.AbstractBaseRawMainController;

@Controller
@RequestMapping("raw/material")
public class MaterialMainController extends AbstractBaseRawMainController {

	@PostConstruct
	private void unit() {
		this.label = "material";
	}
	
}

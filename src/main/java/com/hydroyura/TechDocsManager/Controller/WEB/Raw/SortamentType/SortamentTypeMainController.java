package com.hydroyura.TechDocsManager.Controller.WEB.Raw.SortamentType;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractImpl.AbstractBaseRawMainController;

@Controller
@RequestMapping("raw/sortament-type")
public class SortamentTypeMainController extends AbstractBaseRawMainController {

	@PostConstruct
	private void unit() {
		this.label = "sortament-type";
	}
	
}

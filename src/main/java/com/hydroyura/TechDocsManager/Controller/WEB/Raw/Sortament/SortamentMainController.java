package com.hydroyura.TechDocsManager.Controller.WEB.Raw.Sortament;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydroyura.TechDocsManager.Controller.WEB.Raw.AbstractImpl.AbstractBaseRawMainController;

@Controller
@RequestMapping("raw/sortament")
public class SortamentMainController extends AbstractBaseRawMainController {

	@PostConstruct
	private void unit() {
		this.label = "sortament";
	}
	
}

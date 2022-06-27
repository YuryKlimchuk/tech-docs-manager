package com.hydroyura.TechDocsManager.Controller.WEB;

import java.security.Principal;

//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AbstractController {
	
	@ModelAttribute("userName")
	public String getUserName(Principal principal) {
		//return principal.getName();
		return "KLIMCHUK";
	}
	
	@ModelAttribute("role")
	public String getRoles() {
		return "YURY";
		//return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().getAuthority();
	}
}

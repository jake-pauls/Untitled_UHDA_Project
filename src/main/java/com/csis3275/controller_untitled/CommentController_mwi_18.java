/**
 * 
 */
package com.csis3275.controller_untitled;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.model_untitled.Comment_mwi_18;

/**
 * @author Michael Wilson 300278118
 * @date Nov. 25, 2020
 * CommentController_mwi_18.java
 * com.csis3275.controller_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Controller
public class CommentController_mwi_18 {
	
	@ModelAttribute("comment")
	public Comment_mwi_18 setUpName() {
		return new Comment_mwi_18();
	}
	
	
	
}

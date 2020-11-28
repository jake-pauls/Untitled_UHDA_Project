/**
 * 
 */
package com.csis3275.controller_untitled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.csis3275.dao_untitled.CommentDAO_mwi_18;
import com.csis3275.model_untitled.Comment_mwi_18;
import com.csis3275.model_untitled.EmailService_untitled;

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
	
	@Autowired
	CommentDAO_mwi_18 commentDao;
	
	@Autowired
	EmailService_untitled emailService;
	
	@ModelAttribute("comment")
	public Comment_mwi_18 setUpName() {
		return new Comment_mwi_18();
	}
	
	
	
	@PostMapping("/createComment")
	public RedirectView createComment(@RequestParam("redirectUrl") String redirectUrl,RedirectAttributes redirectAttributes,
			ModelAndView modelAndView, @ModelAttribute("comment")Comment_mwi_18 comment) {
		RedirectView redirectView = new RedirectView("/"+redirectUrl,true);
		if(commentDao.insertComment(comment)) {
			redirectAttributes.addFlashAttribute("successMessage", "Comment was written");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to write comment");
		}
		
		
		
		
		return redirectView;
	}
	
	
	
	public void commentCreateEmail(String userEmail, String subjectLine, String message) {
		SimpleMailMessage commentCreateEmail = new SimpleMailMessage();
		commentCreateEmail.setFrom("uhda.untitled.csis3275@gmail.com");
		commentCreateEmail.setTo(userEmail);
		
		commentCreateEmail.setSubject(subjectLine);
		commentCreateEmail.setText(message);
		emailService.sendEmail(commentCreateEmail);
	}
	
}

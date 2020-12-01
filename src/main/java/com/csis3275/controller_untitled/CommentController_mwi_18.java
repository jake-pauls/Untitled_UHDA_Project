/**
 * 
 */
package com.csis3275.controller_untitled;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.csis3275.dao_untitled.CommentDAO_mwi_18;
import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.Comment_mwi_18;
import com.csis3275.model_untitled.EmailService_untitled;
import com.csis3275.model_untitled.Ticket_untitled;

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
	
	
	//create comment messages
	private final String COMMENT_SUCCESS_MESSAGE = "Comment was written";
	private final String COMMENT_FAILURE_MESSAGE = "Failed to write comment";
	
	//delete comment messages
	private final String COMMENT_DELETE_MEASSAGE = "Comment was deleted";
	private final String COMMENT_DELETE_FAILURE = "Unable to delete comment";
	
	@Autowired
	CommentDAO_mwi_18 commentDao;
	
	@Autowired
	TicketDisplayDAO_mwi_18 ticketDisplayDao;
	
	@Autowired
	EmailService_untitled emailService;
	
	@ModelAttribute("comment")
	public Comment_mwi_18 setUpName() {
		return new Comment_mwi_18();
	}
	
	
	
	@PostMapping("/createComment")
	public RedirectView createComment(@RequestParam("redirectUrl") String redirectUrl,RedirectAttributes redirectAttributes,
			ModelAndView modelAndView, @ModelAttribute("comment")Comment_mwi_18 newComment) {
		RedirectView redirectView = new RedirectView("/"+redirectUrl,true);
		
		Comment_mwi_18 comment = newComment;
		
		//sets the current time to time created in the comment
		comment.setDateCreated(new Timestamp(new Date().getTime()));
		
		if(commentDao.insertComment(comment)) {
			redirectAttributes.addFlashAttribute("successMessage", COMMENT_SUCCESS_MESSAGE);
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", COMMENT_FAILURE_MESSAGE);
		}
		
		Ticket_untitled ticketInUse = ticketDisplayDao.getOneTicket(comment.getTicketId());
		
		if(redirectUrl.equals("employeeHomePage")) {
			String subjectLine = "Ticket: "+ticketInUse.getTicketID()+" has a new comment from "+ticketInUse.getAssignee();
			String message = "The following comment was added to ticket number: "+ticketInUse.getTicketID()+". Title: "+ticketInUse.getTitle()
				+ "\n"+comment.getValue();
			commentCreateEmail(commentDao.getUserEmail(ticketInUse), subjectLine, message);
		}else if(redirectUrl.equals("UserHomePage")) {
			String subjectLine = "Ticket: "+ticketInUse.getTicketID()+" has a new comment from "+ticketInUse.getUsername();
			String message = "The following comment was added to ticket number: "+ticketInUse.getTicketID()+". Title: "+ticketInUse.getTitle()
				+ "\n"+comment.getValue();
			commentCreateEmail(commentDao.getEmployeeEmail(ticketInUse), subjectLine, message);
		}
		
		
		return redirectView;
	}
	
	
	@GetMapping("/deleteComment")
	public RedirectView deleteComment(@RequestParam("redirectUrl") String redirectUrl, @RequestParam("id") int id,
	RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("/"+redirectUrl,true);
		
		if(commentDao.deleteComment(id)) {
			redirectAttributes.addFlashAttribute("successMessage", COMMENT_DELETE_MEASSAGE);
		}else {
			redirectAttributes.addFlashAttribute("errorMessage", COMMENT_DELETE_FAILURE);
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

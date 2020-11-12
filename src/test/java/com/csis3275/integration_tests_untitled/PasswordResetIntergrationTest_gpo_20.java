package com.csis3275.integration_tests_untitled;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.csis3275.dao_untitled.PasswordResetDAO_gpo_20;
import com.csis3275.model_untitled.PasswordReset_gpo_20;
import com.csis3275.model_untitled.User_untitled;

class PasswordResetIntergrationTest_gpo_20 {
	@Autowired
	PasswordResetDAO_gpo_20 passwordResetDAO;
	private PasswordReset_gpo_20 passwordReset = new PasswordReset_gpo_20();
	
	@Test
	void resettingAPasswordWithAResetToken()  {
		String newPassword = "NewPassword";
		passwordReset.setResetToken(UUID.randomUUID().toString());
		User_untitled user = passwordResetDAO.checkUserEmailExists("buno@gmail.com");
		user.setResetToken(passwordReset.getResetToken());
		passwordResetDAO.addResetTokenToUser(user);
		User_untitled userNowWithResetToken = passwordResetDAO.checkUserHasResetToken(user.getResetToken());
		userNowWithResetToken.setPassword(newPassword);
		passwordResetDAO.updatePasswordByResetToken(userNowWithResetToken);
		assertEquals(newPassword, userNowWithResetToken.getPassword());
		
	}

}

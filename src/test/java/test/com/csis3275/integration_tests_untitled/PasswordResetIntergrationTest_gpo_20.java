package test.com.csis3275.integration_tests_untitled;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.csis3275.dao_untitled.PasswordResetDAO_gpo_20;
import com.csis3275.model_untitled.PasswordReset_gpo_20;
import com.csis3275.model_untitled.User_untitled;

import test.com.csis3275.test_config_untitled.TestDatabaseConfig_jpa_66;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig_jpa_66.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest(classes = { PasswordResetDAO_gpo_20.class, PasswordReset_gpo_20.class } )
@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test-table-definitions.sql"),
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:test-table-rollback.sql")
})
class PasswordResetIntergrationTest_gpo_20 {
	
	@Autowired
	PasswordResetDAO_gpo_20 passwordResetDAO;
	
	private PasswordReset_gpo_20 passwordReset;
	
	// Testing Constants
	private static final String USER_EMAIL = "user@UHDATesting.com";
	
	@Test
	void resettingAPasswordWithAResetToken()  {
		passwordReset = new PasswordReset_gpo_20();
		
		// Set the reset token for a user via the password reset object
		passwordReset.setResetToken(UUID.randomUUID().toString());
		User_untitled user = passwordResetDAO.checkUserEmailExists(USER_EMAIL);
		user.setResetToken(passwordReset.getResetToken());
		
		// Update reset token status in database
		passwordResetDAO.addResetTokenToUser(user);
		User_untitled userNowWithResetToken = passwordResetDAO.checkUserHasResetToken(user.getResetToken());
		
		// Set new password
		String newPassword = "NewPassword";
		userNowWithResetToken.setPassword(newPassword);
		passwordResetDAO.updatePasswordByResetToken(userNowWithResetToken);
		assertEquals(newPassword, userNowWithResetToken.getPassword());
		
	}

}

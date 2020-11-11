package com.csis3275.unit_tests_untitled;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import com.csis3275.model_untitled.PasswordReset_gpo_20;

class PasswordResetTest_gpo_20 {
	private final PasswordReset_gpo_20 forgotPassword = new PasswordReset_gpo_20();

	@Test
	void testStoringRandomizedResetToken() {
		String resetToken = UUID.randomUUID().toString();
		forgotPassword.setResetToken(resetToken);
		assertEquals(resetToken, forgotPassword.getResetToken());
	}

}

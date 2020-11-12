/**
 * 
 */
package test.com.csis3275.unit_tests_untitled;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csis3275.dao_untitled.Login_RegisterDAO_mwi_18;
import com.csis3275.model_untitled.User_untitled;

/**
 * @author Michael Wilson 300278118
 * @date Nov. 11, 2020
 * UserActionTest_mwi_18.java
 * com.csis3275.unit_tests_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Component
public class UserActionTest_mwi_18 {

	
	
	@Test
	public void testCreatingUser() {
		
		
		User_untitled user = new User_untitled();
		user.setUsername("tester");
		user.setPassword("password");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setEmail("test@gmail.com");
		user.setSecurityQ("What is your favorite movie?");
		user.setSecurityA("Good Movie");
		user.setRole("user");
		
		
		assertEquals(null, user.getResetToken());
		assertEquals("tester", user.getUsername());
		assertEquals("password",user.getPassword());
		assertEquals("firstName",user.getFirstName());
		assertEquals("lastName",user.getLastName());
		assertEquals("test@gmail.com", user.getEmail());
		assertEquals("What is your favorite movie?", user.getSecurityQ());
		assertEquals("Good Movie", user.getSecurityA());
		assertEquals("user", user.getRole());
	}

}

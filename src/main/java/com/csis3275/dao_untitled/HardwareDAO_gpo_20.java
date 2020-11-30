package com.csis3275.dao_untitled;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.HardwareList_gpo_20;
import com.csis3275.model_untitled.HardwareTypeRowMapper_gpo_20;
import com.csis3275.model_untitled.HardwareTypes_gpo_20;
import com.csis3275.model_untitled.User_untitled;
import com.csis3275.utility_untitled.DatabaseAuthenticationUtilities_untitled;

/**
 * 
 * @author Gregory Pohlod Student ID 300311820
 * @date Nov 29, 2020
 * HardwareDAO_gpo_20.java
 * com.csis3275.dao_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 *
 */

@Component
public class HardwareDAO_gpo_20 {
	JdbcTemplate jdbcTicketManagementTemplate;

	
	//Tickets Table Queries
	private final String SQL_INSERT_NEW_HARDWARE = "INSERT INTO hardwareassignment" + 
			 "(hardwareTypeName, status, usernameAssignedTo, dateAssigned)" +
			 " VALUES (?, ?, ?, ?)";
	private final String SQL_GET_LIST_OF_HARDWARE_TYPES = "SELECT * FROM HARDWARETYPE";
	private final String SQL_DELETE_HARDWARE_TYPE = "DELETE FROM HARDWARETYPE WHERE HARDWARETYPEID = ?";
	private final String SQL_INSERT_NEW_HARDWARE_TYPE = "INSERT INTO HARDWARETYPE (HARDWARETYPEDESCRIPTION) VALUES (?)";
	
	@Autowired
	public HardwareDAO_gpo_20(DataSource dataSource) {
		jdbcTicketManagementTemplate = new JdbcTemplate(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param hardware associated with the user who submitted the ticket
	 * @return add a new entry into the hardwareassignment table
	 */
	public boolean assignHardware(HardwareList_gpo_20 hardware) {
		return jdbcTicketManagementTemplate.update(SQL_INSERT_NEW_HARDWARE, hardware.getHardwareName(), hardware.getStatus(),
				hardware.getUsernameAssignedTo(),getCurrentTime()) > 0;
	}
	
	public boolean addNewHardWareType(HardwareTypes_gpo_20 hardwareType) {
		return jdbcTicketManagementTemplate.update(SQL_INSERT_NEW_HARDWARE_TYPE, hardwareType.getHardwareTypeDescription()) > 0;
	}

	/**
	 * 
	 * @return list of all hardware Names that can be assigned to employees
	 */
	public List<HardwareTypes_gpo_20> getListOfHardwareAvailable(){
		
		return jdbcTicketManagementTemplate.query(SQL_GET_LIST_OF_HARDWARE_TYPES, new HardwareTypeRowMapper_gpo_20());
	}
	
	/**
	 * 
	 * @param hardwareType is the hardwaretype being deleted
	 * @return delete the hardware type from the list.
	 */
	public boolean deleteHardwareType(HardwareTypes_gpo_20 hardwareType) {
		return jdbcTicketManagementTemplate.update(SQL_DELETE_HARDWARE_TYPE, hardwareType.getHardwareTypeID()) > 0;
	}
	/**
	 * Creates a Timestamp object for the current time
	 * @return Timestamp object representing the current time 
	 */
	public Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}
}

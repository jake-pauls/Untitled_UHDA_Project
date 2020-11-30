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
import com.csis3275.model_untitled.UserRowMapper_mwi_18;
import com.csis3275.model_untitled.User_untitled;

@Component
public class HardwareDAO_gpo_20 {
	JdbcTemplate jdbcTicketManagementTemplate;

	//Tickets Table Queries
	private final String SQL_INSERT_NEW_HARDWARE = "INSERT INTO hardwareassignment" + 
			 "(hardwareTypeName, status, usernameAssignedTo, dateAssigned, dateReturned)" +
			 " VALUES (?, ?, ?, ?, ?)";
	private final String SQL_GET_LIST_OF_HARDWARE_TYPES = "SELECT * FROM HARDWARETYPE";
	
	@Autowired
	public HardwareDAO_gpo_20(DataSource dataSource) {
		jdbcTicketManagementTemplate = new JdbcTemplate(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	public boolean assignHardware(HardwareList_gpo_20 hardware) {
		return jdbcTicketManagementTemplate.update(SQL_INSERT_NEW_HARDWARE, hardware.getHardwareName(), hardware.getStatus(),
				hardware.getUsernameAssignedTo(),getCurrentTime()) > 0;
	}

	/**
	 * 
	 * @return list of all hardware Names that can be assigned to employees
	 */
	public List<HardwareTypes_gpo_20> getListOfHardwareAvailable(){
		
		return jdbcTicketManagementTemplate.query(SQL_GET_LIST_OF_HARDWARE_TYPES, new HardwareTypeRowMapper_gpo_20());
	}
	
	/**
	 * Creates a Timestamp object for the current time
	 * @return Timestamp object representing the current time 
	 */
	public Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}
}

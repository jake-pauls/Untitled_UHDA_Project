package com.csis3275.model_untitled;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Gregory Pohlod Student ID 300311820
 * @date Nov 28, 2020 HardwareRowMapper_gpo_20.java com.csis3275.model_untitled
 *       CSIS 3275 Group Project Group Name: Untitled
 *
 */
@Component
public class HardwareRowMapper_gpo_20 implements RowMapper<HardwareList_gpo_20> {

	@Override
	public HardwareList_gpo_20 mapRow(ResultSet rs, int rowNum) throws SQLException {
		HardwareList_gpo_20 hardware = new HardwareList_gpo_20();

		hardware.setHardwareID(rs.getInt("hardwareID"));
		hardware.setHardwareName(rs.getString("hardwareName"));
		hardware.setStatus(rs.getString("hardwareStatus"));
		hardware.setUsernameAssignedTo(rs.getString("hardwareUser"));
		hardware.setDateAssigned(rs.getTimestamp("hardwareAssignedDate"));
		hardware.setDateReturned(rs.getTimestamp("hardwareReturnedDate"));

		return hardware;
	}

}

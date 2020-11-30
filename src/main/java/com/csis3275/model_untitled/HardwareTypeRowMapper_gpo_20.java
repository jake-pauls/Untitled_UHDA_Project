package com.csis3275.model_untitled;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class HardwareTypeRowMapper_gpo_20 implements RowMapper<HardwareTypes_gpo_20> {

	@Override
	public HardwareTypes_gpo_20 mapRow(ResultSet rs, int rowNum) throws SQLException {
		HardwareTypes_gpo_20 hardwareTypes = new HardwareTypes_gpo_20();

		hardwareTypes.setHardwareID(rs.getInt("hardwareTypeID"));
		hardwareTypes.setHardwareTypeDescription(rs.getString("hardwareTypeDescription"));

		return hardwareTypes;
	}

}

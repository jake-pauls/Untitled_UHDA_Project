/**
 * 
 */
package com.csis3275.model_untitled;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Michael Wilson 300278118
 * @date Nov. 25, 2020
 * CommentRowMapper_mwi_18.java
 * com.csis3275.model_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */
public class CommentRowMapper_mwi_18 implements RowMapper<Comment_mwi_18>{

	@Override
	public Comment_mwi_18 mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Comment_mwi_18 comment = new Comment_mwi_18();
		comment.setCommentId(rs.getInt("commentId"));
		comment.setTicketId(rs.getInt("ticketId"));
		comment.setAuthor(rs.getString("author"));
		comment.setValue(rs.getString("value"));
		comment.setDateCreated(rs.getTimestamp("dateCreated"));
		
		return comment;
	}

}

/**
 * 
 */
package com.csis3275.dao_untitled;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.CommentRowMapper_mwi_18;
import com.csis3275.model_untitled.Comment_mwi_18;

/**
 * @author Michael Wilson 300278118
 * @date Nov. 25, 2020
 * CommentDAO_mwi_18.java
 * com.csis3275.dao_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Component
public class CommentDAO_mwi_18 {
	
	JdbcTemplate template;
	
	private final String SQL_GET_COMMENTS = "SELECT * FROM Comments WHERE ticketId = ?;";
	private final String SQL_CREATE_COMMENT = "INSERT INTO Comments(ticketId,author,value,dateCreated) VALUES (?,?,?,?);";
	
	@Autowired
	public CommentDAO_mwi_18(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	public Boolean insertComment(Comment_mwi_18 comment) {
		return template.update(SQL_CREATE_COMMENT,comment.getTicketId(),comment.getAuthor(),comment.getValue(),comment.getDateCreated()) > 0;
	}
	
	public List<Comment_mwi_18> getComments(int id){
		return template.query(SQL_GET_COMMENTS, new CommentRowMapper_mwi_18(), id);
	}
}

package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.CommentDAO;
import com.revature.utils.ConnectionUtil;

public class CommentPostgres implements CommentDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public int create(Comment dataToAdd) {
		int generatedId=1;
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String[] keys = {"comment_id"};
			String sql="insert into comment"
					+ " (comment_text,"
					+ " req_id,"
					+ " approver_id,"
					+ " sent_at)"
					+ " values (?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql,keys);
			pStmt.setString(1, dataToAdd.getCommentText());
			pStmt.setInt(2, dataToAdd.getRequest().getReqId());
			pStmt.setInt(3, dataToAdd.getApprover().getEmpId());
			pStmt.setTimestamp(4, Timestamp.valueOf(dataToAdd.getSentAt()));
			
			pStmt.executeUpdate();
			ResultSet generatedKeys = pStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				generatedId = generatedKeys.getInt(1);
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}
	
	@Override
	public Comment getById(int id) {
		Comment comm = new Comment();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from comment where comment_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			int commId = resultSet.getInt("comment_id");
			String text = resultSet.getString("comment_text");
			int appId = resultSet.getInt("approver_id");
			int reqId = resultSet.getInt("req_id");
			LocalDateTime ts = resultSet.getTimestamp("sent_at").toLocalDateTime();
			
			comm.setCommentId(commId);
			comm.setCommentText(text);
			Employee emp = new Employee(appId);
			comm.setApprover(emp);
			Reimbursement req = new Reimbursement(reqId);
			comm.setRequest(req);
			comm.setSentAt(ts);
		}
	
	 catch (SQLException e) {
		e.printStackTrace();
	}
	return comm;
}

	@Override
	public Set<Comment> getAll() {
		Set<Comment> comments = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from comment";
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next()) {
				int commId = resultSet.getInt("comment_id");
				String text = resultSet.getString("comment_text");
				int appId = resultSet.getInt("approver_id");
				int reqId = resultSet.getInt("req_id");
				LocalDateTime ts = resultSet.getTimestamp("sent_at").toLocalDateTime();
				
				Comment comm = new Comment();
				comm.setCommentId(commId);
				comm.setCommentText(text);
				Employee emp = new Employee(appId);
				comm.setApprover(emp);
				Reimbursement req = new Reimbursement(reqId);
				comm.setRequest(req);
				comm.setSentAt(ts);
				
				comments.add(comm);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	@Override
	public Set<Comment> getByRequestId(int reqId) {
		Set<Comment> comments = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from comment where req_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reqId);
			
			ResultSet resultSet = pStmt.executeQuery();
			

			while (resultSet.next()) {
				int commId = resultSet.getInt("comment_id");
				String text = resultSet.getString("comment_text");
				int appId = resultSet.getInt("approver_id");
				int reqid = resultSet.getInt("req_id");
				LocalDateTime ts = resultSet.getTimestamp("sent_at").toLocalDateTime();
				
				Comment comm = new Comment();
				comm.setCommentId(commId);
				comm.setCommentText(text);
				Employee emp = new Employee(appId);
				comm.setApprover(emp);
				Reimbursement req = new Reimbursement(reqid);
				comm.setRequest(req);
				comm.setSentAt(ts);
				
				comments.add(comm);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

}

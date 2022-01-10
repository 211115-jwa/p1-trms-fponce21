package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.postgres.CommentPostgres;
import com.revature.utils.DAOFactory;

public class CommentDaoTest {
	private CommentDAO comm = DAOFactory.getCommentDAO();
	
	@BeforeEach
	public void setup() {
		comm = new CommentPostgres();
	}
	
	@Test
	public void  createTest() {
		Comment c1 = new Comment();
		Reimbursement req = new Reimbursement(1);
		c1.setRequest(req);
		Employee approver = new Employee(22);
		c1.setApprover(approver);
		assertNotEquals(0, comm.create(c1));
		
	}
	
	@Test
	public void getAllNotNull() {
		Set<Comment> act = comm.getAll();
		assertNotEquals(null, act);
		
	}
	
	@Test
	public void getByIdNotNull() {
		Comment c1 = comm.getById(1);
		assertNotEquals(null, c1);
	}
	
	@Test
	public void getByReqIdNotNull () {
		Set <Comment> c1 = comm.getByRequestId(1);
		assertNotEquals(null, c1);
		
	}
	
	@Test
	public void getByValidComment() {
		String expected = "Pending Approval";
		Comment c1 = comm.getById(2);
		String act = c1.getCommentText();
		assertEquals(expected, act);
	}
	

}

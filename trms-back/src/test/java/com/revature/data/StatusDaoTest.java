package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Status;
import com.revature.data.postgres.StatusPostgres;
import com.revature.utils.DAOFactory;

public class StatusDaoTest {
	private StatusDAO statd = DAOFactory.getStatusDAO();
	
	@BeforeEach
	public void setup() {
		statd = new StatusPostgres();
	}
	
	@Test
	public void getByValidNameStatus() {
		String exp = "Approved";
		Status stat = statd.getByName(exp);
		String act = stat.getName();
		assertEquals(exp, act);
		
		
	}
	
	@Test
	public void getByInvalidNameStatus() {
		String exp = "Appvd";
		String real = "Approved";
		Status stat = statd.getByName(real);
		String act = stat.getName();
		assertNotEquals(exp, act);
	}
	
	@Test
	public void getByNameNotNull () {
		String name = "Denied";
		Status act = statd.getByName(name);
		assertNotEquals(null, act);
		
	}
	
	@Test
	public void getByValidIdStatus() {
		String exp = "Approved";
		Status three = statd.getById(3);
		String act = three.getName();
		assertEquals(exp, act);
		
	}
	@Test
	public void getByIdNotNull() {
		Status act = statd.getById(2);
		assertNotEquals(null, act);
		
	}
	
	@Test
	public void getAllNotNull() {
		Set<Status> act = statd.getAll();
		assertNotEquals(null, act);
		
	}
	
	
	

}

package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.data.postgres.DepartmentPostgres;
import com.revature.utils.DAOFactory;

public class DepartmentDaoTest {
	
	private DepartmentDAO d = DAOFactory.getDepartmentDAO();
	
	@BeforeEach
	public void setup() {
		d = new DepartmentPostgres();
	}
	
	@Test
	public void getByValidDepartment() {
		String expected ="Training";
		Department two = d.getById(2);
		String actual = two.getName();
		assertEquals(expected,actual);
	}
	@Test
	public void getByInvalidDepartment() {
		String expected = "HR";
		String real = "Human Resources";
		Department dept = d.getByName(real);
		String actual = dept.getName();
		assertNotEquals(expected, actual);
		
	}
	
	@Test
	public void getByNameValidDepartment() {
		String expected = "Business Development";
		Department dept = d.getByName(expected);
		String actual = dept.getName();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void getbyIdNotNull() {
		Department actual = d.getById(2);
		assertNotEquals(null, actual);
		
	}
	
	@Test
	public void getByDeptNotNull() {
		String dept = "IT";
		Department actual = d.getByName(dept);
		assertNotEquals(null, actual);
	}
	
	@Test
	public void getAllNotNull() {
		Set <Department> actual = d.getAll();
		assertNotEquals(null, actual);
		
	}
	
}

package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.data.postgres.EmployeePostgres;
import com.revature.utils.DAOFactory;

public class EmployeeDaoTest {
	
	private EmployeeDAO empd = DAOFactory.getEmployeeDAO();
	
	@BeforeEach
	public void setup() {
		empd = new EmployeePostgres();
	}
	
	@Test
	public void createTest() {
		Department training = new Department(2,"Training",2);
		Employee create = new Employee();
		create.setDepartment(training);
		Employee trev = new Employee(55);
		create.setSupervisor(trev);
		assertNotEquals(55, empd.create(create));
	}
	
	@Test
	public void getByIdValidEmployee() {
		String expected = "rblanking1";
		Employee one = empd.getById(1);
		String actual = one.getUsername();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void getByIdNotNull() {
		Employee real = empd.getById(2);
		assertNotEquals(null, real);
		
	}
	
	@Test
	public void getAllNotNull() {
		Set<Employee> actual = empd.getAll();
		assertNotEquals(null, actual);
	}
	
	@Test
	public void getByUsernameNotNull() {
		String expected = "rblanking1";
		Employee emp = empd.getByUsername(expected);
		String actual = emp.getUsername();
		assertNotEquals(null, actual);
		
	}
	
	@Test
	public void getbyUsernameInvalidEmp() {
		String expected = "ponce";
		String real = "rblanking1";
		Employee emp = empd.getByUsername(real);
		String actual = emp.getUsername();
		assertNotEquals(expected, actual);
	}
	
	@Test
	public void getByUsernameValidEmp() {
		String expected = "vwraggs2";
		Employee emp = empd.getByUsername(expected);
		String actual = emp.getUsername();
		assertEquals(expected, actual);
	}
	
	
	
	

}

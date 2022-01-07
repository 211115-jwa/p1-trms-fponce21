package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
		Employee meg = new Employee(52);
		create.setSupervisor(meg);
		assertNotEquals(0, empd.create(create));
	}
	
	@Test
	public void getByIdNotNull() {
		Employee real = empd.getById(2);
		assertNotEquals(null, real);
		
	}
	
	@Test
	public void getByIdValidEmployee() {
		String expected = "rblanking1";
		Employee one = empd.getById(1);
		String actual = one.getUsername();
		assertEquals(expected, actual);
		
	}

}

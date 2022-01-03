package com.revature.data;

import com.revature.data.postgres.EmployeePostgres;


public interface EmployeeDAOTest {

	public EmployeeDAO employeeDao = new EmployeePostgres();
	
	
}

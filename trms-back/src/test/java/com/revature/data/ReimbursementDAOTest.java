package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.utils.DAOFactory;

public class ReimbursementDaoTest {

	private ReimbursementDAO reimb = DAOFactory.getReimbursementDAO();
	private EmployeeDAO empd = DAOFactory.getEmployeeDAO();
	
	@Test
	public void createTest() {
		GradingFormat grf = new GradingFormat();
		EventType etp = new EventType();
		Status stat = new Status();
		Reimbursement create = new Reimbursement();
		LocalDate day = LocalDate.of(2022, 01, 8);
		LocalTime time = LocalTime.of(8, 19);
		int id = 52;
		
		create.getRequestor().setEmpId(id);
		create.setEventDate(day);
		create.setEventTime(time);
		create.setEventType(etp);
		create.setGradingFormat(grf);
		create.setStatus(stat);
		assertNotEquals(0, reimb.create(create));
	}
	
	@Test
	public void getAllNotNull() {
		Set<Reimbursement> actual = reimb.getAll();
		assertNotEquals(null, actual);
		
	}
	
	@Test
	public void getByIdNotNull() {
		Reimbursement actual = reimb.getById(2);
		assertNotEquals(null, actual);
		
	}
	
	
		
}
	


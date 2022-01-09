package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		LocalDate day = LocalDate.of(2021, 06, 22);
		LocalTime time = LocalTime.of(22, 25);
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
	
	@Test
	public void getByRequestorNotnull() {
		Employee emp1 = empd.getById(16);
		Set<Reimbursement> actual = reimb.getByRequestor(emp1);
		assertNotEquals(null, actual);
		
	}
	
	/*@Test
	public void getByIdValidReimbursement() {
		String expected = "2021-07-18 03:44:44";
		Reimbursement r1 = reimb.getById(4);
		LocalDate d = r1.getEventDate();
		LocalTime t = r1.getEventTime();
		d.toString();
		t.toString();
		String actual = d + " " + t;
		assertEquals(expected, actual);
		
		}*/
	
	@Test
	public void getReimbursementByValidRequestor() {
		Employee emp1 = empd.getById(16);
		Set<Reimbursement> reqs = reimb.getByRequestor(emp1);
		Employee actual = new Employee();
		for (Reimbursement req : reqs) {
			actual = req.getRequestor();
		}
		
	}
	
	/*@Test
	public void getReimbursementByInvalidRequestor() {
		Employee emp1 = empd.getById(200);
		Employee r = empd.getById(1);
		Reimbursement req = reimb.getByRequestor(emp1);
		Employee actual = req.getRequestor();
		assertNotEquals(emp1, actual);
	}*/
	
	
		
}
	


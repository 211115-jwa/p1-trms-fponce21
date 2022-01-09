package com.revature.services;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Comment;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.DepartmentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.StatusDAO;
import com.revature.exceptions.WrongUsernmPsswrdException

@ExtendWith(MockitoExtension.class)

public class EmployeeServiceTest {
	
	@Mock
	private StatusDAO stat;
	@Mock
	private EventTypeDAO evt;
	@Mock
	private CommentDAO comm;
	@Mock
	private DepartmentDAO dept;
	@Mock
	private EmployeeDAO emp;
	
	@Mock
	private Reimbursement reimb;
	@Mock
	private GradingFormatDAO gradform;
	
	@InjectMocks
	private EmployeeService empserv = new EmployeeServiceImpl();
	
	private static Set<Comment> mockComments;
	private static Set<Comment> mockCommentsWhereReqIs7;
	private static Set<Status> mockStatus;
	private static Set<Employee> mockEmployee;
	private static Set<Object> mockGradingFormat;
	private static Set<Reimbursement> mockRequests;
	private static Set<Objects> mockEventTypes;
	private static Set<Role> mockRole;
	
	static Status mockStat1 = new Status(1, "Pending Approval", "Supervisor");
	static Status mockStat2 = new Status(2, "Approved", "Supervisor");
	static Status mockStat3 = new Status(3, "Denied", "Supervisor");
	static Role mockRole1 = new Role(1, "User");
	static Role mockRole2 = new Role(2, "Supervisor");
	static EventType evntTyp = new EventType(1, "University Course", 60.0);
	static GradingFormat grdFrmt = new GradingFormat(1, "Pass/Fail", "Pass");
	Department mockDept = new Department(1, "Test Department", 24);
	
	@BeforeAll
	public static void mockEventTypes() {
		mockEventTypes = new HashSet<>();
		mockEventTypes.add(evntTyp);
	}
	
	@BeforeAll
	public static void mockGradingFormat() {
		mockGradingFormat = new HashSet<>();
		mockGradingFormat.add(grdFrmt);	
	}
	
	@BeforeAll
	public static void mockrequestSetUp() {
	mockRequests = new HashSet<>();
		
		for (int i=1; i<=8; i++) {
			Reimbursement req = new Reimbursement();
			req.setReqId(i);
			req.getRequestor().setEmpId(15);
			for (int j=1; j<=3; j++) {
				req.getStatus().setStatusId(j);
			}
			mockRequests.add(req);
		}
	}
	
	@BeforeAll
	public static void mockRoleSetup() {
		mockRole = new HashSet<>();
		
		mockRole.add(mockRole1);
		mockRole.add(mockRole2);
	}
	
	@BeforeAll
	public static void mockCommentsSetup() {
		mockComments = new HashSet<>();
		mockCommentsWhereReqIs7 = new HashSet<>();
		
		for (int i=1; i<=24; i++) {
			Comment comm = new Comment();
			comm.setCommentId(i);
			for (int j=1; j<=8; j++) {
				comm.setRequest(new Reimbursement());
				comm.getRequest().setReqId(j);
			}
			comm.setApprover(new Employee());
			comm.getApprover().setEmpId(24);
			mockComments.add(comm);
			if(comm.getRequest().getReqId()==7){
				mockCommentsWhereReqIs7.add(comm);
			}
		}
	}
	@BeforeAll
	public static void mockEmployeesSetup() {
		mockEmployee = new HashSet<>();
		
		for (int i=1; i<=24; i++) {
			Employee emp = new Employee();
			emp.setEmpId(i);
			emp.setFirstName("First"+i);
			emp.setLastName("Last"+i);
			emp.setUsername("usrname" + i);
			emp.setPassword("passwd");
			emp.setFunds(1000.00);
			emp.getSupervisor().setEmpId(24);
			if(i==24) emp.getRole().setRoleId(2);
			else emp.getRole().setRoleId(1);
			mockEmployee.add(emp);	
		}
	}
	
	@BeforeAll
	public static void mockStatusSetup() {
		mockStatus = new HashSet<>();
		
		mockStatus.add(mockStat1);
		mockStatus.add(mockStat2);
		mockStatus.add(mockStat3);
	}
		


	@Test
	public void logInUsernameDoesNotExist() throws WrongUsernmPsswrdException{
		
	}
	
}
	


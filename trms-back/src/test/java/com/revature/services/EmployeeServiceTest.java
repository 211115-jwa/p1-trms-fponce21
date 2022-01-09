package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Map;
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
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.exceptions.WrongUsrPsswrdException;


@ExtendWith(MockitoExtension.class)

public class EmployeeServiceTest {
	
	@Mock
	private CommentDAO commd;
	
	@Mock
	private DepartmentDAO deptd;
	
	@Mock
	private EmployeeDAO empd;
	
	@Mock
	private EventTypeDAO evtd;
	
	@Mock
	private GradingFormatDAO grfd;
	
	@Mock
	private ReimbursementDAO reqd;
	
	@Mock
	private StatusDAO statd;
	
	@InjectMocks
	private EmployeeService empserv = new EmployeeServiceImpl();
	
	
	private static Set<Employee> mockEmployees;
	private static Set<Reimbursement> mockRequests;
	private static Set<Comment> mockComments;
	private static Set<Comment> mockCommentsWhereReqIs7;
	private static Set<Status> mockStatus;
	private static Set<Role> mockRole;
	private static Set<Object> mockEventTypes;
	private static Set<Object> mockGradingFormats;
	
	static Status mockStat1 = new Status(1, "Pending Approval", "System");
	static Status mockStat2 = new Status(2, "Denied", "Supervisor");
	static Status mockStat3 = new Status(3, "Approved", "Supervisor");
	static Role mockRl1 = new Role(1, "User");
	static Role mockRl2 = new Role(2, "Supervisor");
	static EventType evntTp = new EventType(5, "Technical Training", 90.0);
	static GradingFormat gradForm = new GradingFormat(3, "Percentage", "80");
	Department mockDpt = new Department(1, "Mock Department", 30);
	
	@BeforeAll
	public static void mockEventTypes() {
		mockEventTypes = new HashSet<>();
		mockEventTypes.add(evntTp);
	}
	
	@BeforeAll
	public static void mockGradingFormats() {
		mockGradingFormats = new HashSet<>();
		mockGradingFormats.add(gradForm);
	}
	
	@BeforeAll
	public static void mockStatusSetup() {
		mockStatus = new HashSet<>();
		
		mockStatus.add(mockStat1);
		mockStatus.add(mockStat2);
		mockStatus.add(mockStat3);
	}
	
	@BeforeAll
	public static void mockRoleSetup() {
		mockRole = new HashSet<>();
		
		mockRole.add(mockRl1);
		mockRole.add(mockRl2);
	}
	
	@BeforeAll
	public static void mockRequestsSetup() {
		mockRequests = new HashSet<>();
		
		for (int i=1; i<=8; i++) {
			Reimbursement req = new Reimbursement();
			req.setReqId(i);
			req.getRequestor().setEmpId(10);
			for (int j=1; j<=3; j++) {
				req.getStatus().setStatusId(j);
			}
			mockRequests.add(req);
		}
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
			if(comm.getRequest().getReqId()==3){
				mockCommentsWhereReqIs7.add(comm);
			}
		}
	}
	
	@BeforeAll
	public static void mockEmployeesSetup() {
		mockEmployees = new HashSet<>();
		
		for (int i=1; i<=30; i++) {
			Employee emp = new Employee();
			emp.setEmpId(i);
			emp.setFirstName("First"+i);
			emp.setLastName("Last"+i);
			emp.setUsername("User" + i);
			emp.setPassword("passwd");
			emp.setFunds(2000.00);
			emp.getSupervisor().setEmpId(30);
			if(i==30) emp.getRole().setRoleId(2);
			else emp.getRole().setRoleId(1);
			mockEmployees.add(emp);	
		}
	}
	
	@Test
	public void successfullyGetEmployeeById() {
		Employee emp = new Employee();
		emp.setEmpId(2);
		
		when(empd.getById(10)).thenReturn(emp);
		Employee actual = empserv.getEmployeeById(10);
		assertEquals(emp, actual);
	}
	
	@Test
	public void unsuccessfullyGetEmployeeById() {
		when(empd.getById(10)).thenReturn(null);
		
		Employee actual = empserv.getEmployeeById(10);
		assertNull(actual);
	}
	
	@Test
	public void successfullyAddNewComment() {
		Comment comm = new Comment();
		
		when(commd.create(comm)).thenReturn(5);
		
		int id = empserv.addComment(comm);
		
		assertEquals(5,id);
	}
	
	@Test
	public void unsuccessfullyAddNewComment() {
		Comment comm = new Comment();
		
		when(commd.create(comm)).thenReturn(0);
		
		int id = empserv.addComment(comm);
		
		assertEquals(0,id);
	}
	
	@Test
	public void successfullyGetCommentsForAReimbursementRequest() {
		Reimbursement req = new Reimbursement();
		req.setReqId(7);
		
		when(commd.getByRequestId(7)).thenReturn(mockCommentsWhereReqIs7);
		
		Set<Comment> comm = empserv.getComments(req);
		
		assertEquals(mockCommentsWhereReqIs7,comm);
	}
	
	@Test
	public void unsuccessfullyGetCommentsForAReimbursementRequest() {
		Reimbursement req = new Reimbursement();
		req.setReqId(10);
				
		Set<Comment> comm = empserv.getComments(req);
		
		assertTrue(comm.isEmpty());
	}
	
	@Test
	public void getRequestOptionsSuccessfully() {
		
		when(evtd.getAll()).thenReturn(mockEventTypes);
		when(grfd.getAll()).thenReturn(mockGradingFormats);
		Map<String, Set<Object>> actualRequestOptions = empserv.getRequestOptions();
		assertNotNull(actualRequestOptions);
		
	}
	
	@Test
	public void submittingSuccessfulReimbursementRequest() {
		Reimbursement req = new Reimbursement();
		
		when(reqd.create(req)).thenReturn(5);
		
		int id = empserv.submitReimbursementRequest(req);
		
		assertNotEquals(0,id);
	}
	
	@Test
	public void submittingUnsuccessfulReimbursementRequest() {
		Reimbursement req = new Reimbursement();
		
		when(reqd.create(req)).thenReturn(0);
		
		int id = empserv.submitReimbursementRequest(req);
		
		assertEquals(0,id);
	}
	
	@Test
	public void successfullyGetReimbursementReguests() {
		Employee emp = new Employee();
		emp.setEmpId(1);
		
		when(reqd.getByRequestor(emp)).thenReturn(mockRequests);
		
		Set<Reimbursement> reqs = empserv.getReimbursementRequests(emp);
		
		assertEquals(mockRequests,reqs);
	}
	
	@Test
	public void unsuccessfullyGetReimbursementReguests() {
		Employee emp = new Employee();
		emp.setEmpId(5);
		
		Set<Reimbursement> reqs = empserv.getReimbursementRequests(emp);
		
		assertTrue(reqs.isEmpty());
	}
	
	@Test
	public void logInSuccessfully() throws WrongUsrPsswrdException{
		Employee emp = new Employee();
		emp.setEmpId(1);
		String username = emp.getUsername();
		String password = emp.getPassword();
				
		when(empd.getByUsername(username)).thenReturn(emp);
		
		Employee real = empserv.logIn(username, password);
		
		assertEquals(emp, real);
	}
	
	@Test
	public void logInIcorrectPassword() throws WrongUsrPsswrdException{
		Employee emp = new Employee();
		emp.setEmpId(1);
		String user = emp.getUsername();
		String password = "passwd";
		emp.getPassword();
		
		when(empd.getByUsername(user)).thenReturn(emp);
		
		assertThrows(WrongUsrPsswrdException.class, () -> {
			empserv.logIn(user, password);
		});
	}
	
	@Test
	public void logInUsernameDoesNotExist() throws WrongUsrPsswrdException{
		String username = "ponce";
		String password = "passwd";
		
		when(empd.getByUsername(username)).thenReturn(null);
		
		assertThrows(WrongUsrPsswrdException.class, () -> {
			empserv.logIn(username, password);
		});
	}
	


}
	
	
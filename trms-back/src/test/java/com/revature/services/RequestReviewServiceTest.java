package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
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

@ExtendWith(MockitoExtension.class)

public class RequestReviewServiceTest {
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
	private static Set<Reimbursement> mockPendReqs;
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
	public static void mockPendReqsSetup() {
		mockPendReqs = new HashSet<>();
		
		for (int i=1; i<=8; i++) {
			Reimbursement req = new Reimbursement();
			req.setReqId(i);
			req.getRequestor().setEmpId(1);
			req.getStatus().setStatusId(1);
			mockPendReqs.add(req);
		}
	}
	
	@BeforeAll
	public static void mockCommentsSetup() {
		mockComments = new HashSet<>();
		
		for (int i=1; i<=30; i++) {
			Comment comm = new Comment();
			comm.setCommentId(i);
			for (int j=1; i<=3; i++) {
				comm.setRequest(new Reimbursement());
				comm.getRequest().setReqId(j);
				comm.setApprover(new Employee());
				if(i==1) comm.getApprover().setEmpId(1);
				else comm.getApprover().setEmpId(30);
			}
			mockComments.add(comm);
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
	public void getPendingReqsBenefitsco() {
		Employee emp = new Employee();
		Role role = new Role();
		role.setRoleId(5);
		emp.setRole(role);
		
		Status stat = new Status();
		stat.setStatusId(1);
		
		when(reqd.getByStatus(stat)).thenReturn(mockPendReqs);
		
		Set<Reimbursement> actualReq = reqd.getByStatus(stat);
		
		assertEquals(mockPendReqs,actualReq);
	}
	
	@Test
	public void getPendingReqsSupervisor() {
		Employee emp = new Employee();
		Role role = new Role();
		role.setRoleId(2);
		emp.setRole(role);
		
		Status stat = new Status();
		stat.setStatusId(1);
		
		when(reqd.getByStatus(stat)).thenReturn(mockPendReqs);
		
		Set<Reimbursement> actualReq = reqd.getByStatus(stat);
		
		assertEquals(mockPendReqs,actualReq);
	}
	
	@Test
	public void getPendingReqsDeptMngr() {
		Employee emp = new Employee();
		Role role = new Role();
		role.setRoleId(3);
		emp.setRole(role);
		
		Status stat = new Status();
		stat.setStatusId(1);
		
		when(reqd.getByStatus(stat)).thenReturn(mockPendReqs);
		
		Set<Reimbursement> actualReq = reqd.getByStatus(stat);
		
		assertEquals(mockPendReqs,actualReq);
	}
	

}

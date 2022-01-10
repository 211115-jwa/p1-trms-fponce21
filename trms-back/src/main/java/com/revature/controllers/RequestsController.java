package com.revature.controllers;


import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.client.api.Request;

import com.revature.beans.Employee;

import com.revature.beans.Reimbursement;
import com.revature.data.ReimbursementDAO;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.RequestReviewService;
import com.revature.services.RequestReviewServiceImpl;
import com.revature.utils.DAOFactory;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class RequestsController {
	
	
	
	private static RequestReviewService reqS = new RequestReviewServiceImpl();
	private static EmployeeService empS = new EmployeeServiceImpl();
	private static ReimbursementDAO reqD = DAOFactory.getReimbursementDAO();
	private static Logger log = LogManager.getLogger(RequestsController.class);
	
	/**
	 * Retrieves the submitted reimbursement request from the
	 * HTTP request body and sends it to be inserted in the database.
	 * <p>
	 * If the insertion is not successful, sends an HTTP response
	 * with a status code of 400 (Bad Request) and a message stating
	 * that something went wrong.
	 * <p>
	 * If the insertion is successful, sends an HTTP response with
	 * a status code of 201 (Created) and the submitted request with
	 * its newly generated ID.
	 * <p>
	 * This method should be handling a POST request.
	 * 
	 * @param ctx Javalin's Context object representing the HTTP request and response
	 */
	public static void submitReimbursementRequest(Context ctx) {
		
		Reimbursement request = ctx.bodyAsClass(Reimbursement.class);
		//log.info("request object has been submitted:" + request);
		//ctx.result("");
		int reqId = empS.submitReimbursementRequest(request);
		if (reqId != 0) {
			ctx.status(HttpCode.CREATED);
			request.setReqId(reqId);
			ctx.json(request);
		} else {
			ctx.status(400);
			ctx.result("Something went wrong. Please try again.");
		}
	}
	
	/**
	 * Sends an HTTP response containing the reimbursement requests
	 * associated with a particular employee who submitted them based
	 * on that employee's ID (which is sent as a path variable).
	 * <p>
	 * If the ID is of the correct format and the employee exists in
	 * the database, the requests are returned with a status code of 200.
	 * <p>
	 * If the ID is of the correct format but the employee does not
	 * exist, a response is sent with a status code of 404 (Not Found)
	 * and a message stating that the user does not exist.
	 * <p>
	 * If the ID is <strong>not</strong> of the correct format, a
	 * response is sent with a status code of 400 (Bad Request) and
	 * a message stating that the ID must be an integer.
	 * 
	 * @param ctx Javalin's Context object representing the HTTP request and response
	 */
	public static void getRequestsByRequestor(Context ctx) {
		String requestorIdStr = ctx.pathParam("id");
		
		try {
			int requestorId = Integer.valueOf(requestorIdStr);
			Employee requestor = empS.getEmployeeById(requestorId);
			
			if (requestor != null) {
				ctx.json(empS.getReimbursementRequests(requestor));
			} else {
				ctx.status(404);
				ctx.result("The user you specified does not exist.");
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Requestor ID must be an integer. Please try again.");
		}
	}
	public static void getRequestsByApprover(Context ctx) {
		String appIdStr = ctx.pathParam("id");
		
		try {
			int appId = Integer.valueOf(appIdStr);
			Employee approver = empS.getEmployeeById(appId);
			int statId = 1;
			
			if (approver != null) {
				ctx.json(reqS.getPendingReimbursements(approver, statId));
			} else {
				ctx.status(404);
				ctx.result("The user you specified does not exist.");
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Requestor ID must be an integer. Please try again.");
		}
	}
	
	public static void getAllReqs(Context ctx) {
		String username = ctx.queryParam("username");

		if (username != null && !"".equals(username)) {
			Set<Reimbursement> reqs = reqD.getAll();
			ctx.json(reqs);
		} else {
			String error = "Username is empty.";
			ctx.json(error);
		}	
	}
	
	/*public static void updateARequest(Context ctx) {
		log.info("user is updating a pet");
		
		try {
			int empId = Integer.parseInt(ctx.pathParam("id")); // num format exception
			log.debug("pet id to update: " + empId);
			
			Request reqToEdit = ctx.bodyAsClass(Request.class);
			log.debug("pet to update from request body: " + reqToEdit);
			
			if (reqToEdit != null && reqToEdit.get == empId) {
				reqToEdit = empS.editEmp(reqToEdit);
				if (reqToEdit != null)
					ctx.json(petToEdit);
				else
					ctx.status(404);
			} else {
				// conflict: the id doesn't match the id of the pet sent
				ctx.status(HttpCode.CONFLICT);
			}
		} catch (NumberFormatException e) {
			log.error("NumberFormatException for pet id");
			ctx.status(400);
			ctx.result("Pet ID must be a numeric value");
		}
	}
	
	/*public static void updateARequest(Context ctx) {
		Reimbursement updReq = ctx.bodyAsClass(Reimbursement.class);
		//log.info("request object has been submitted:" + request);
		//ctx.result("");
		int reqId = empS.u
		//if (reqId != 0) {
			//ctx.status(HttpCode.CREATED);
			//request.setReqId(reqId);
			//ctx.json(request);
	//	} else {
		//	ctx.status(400);
		//	ctx.result("Something went wrong. Please try again.");
	//	}
	}
	
		
		
	}*/
	
	
}

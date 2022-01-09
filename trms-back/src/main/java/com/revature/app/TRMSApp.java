package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.RequestsController;

import io.javalin.Javalin;

public class TRMSApp {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		});
		
		app.start();
		
		/* this makes sure that anything beyond a basic "get all requests"
		// requires a login token
		app.before("/requests/*", ctx -> {
			String token = ctx.header("Token");
			if (token==null) ctx.status(HttpCode.UNAUTHORIZED);
		});*/
		
		app.routes(() -> {
			
			path("/reqs", () -> {
				
				get(RequestsController::getAllReqs);
				post(RequestsController::submitReimbursementRequest);

				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});	
				path("/approver/{id}", () -> {
					get(RequestsController::getRequestsByApprover);
					//put(RequestsController::updateARequest);
					
				});
			});
		});
		
		path("/employees", () -> {
			
			path("/auth", () -> {
				post(EmployeeController::logIn); // login
			});
			path("/{id}", () -> {
				get(EmployeeController::getEmpById); // get user by id
				path("/auth", () -> {
					get(EmployeeController::checkLogin); // check login
				});
			});
		});
		
	}

}

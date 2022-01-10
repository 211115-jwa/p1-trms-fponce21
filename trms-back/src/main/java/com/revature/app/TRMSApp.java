package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.EmployeeController;

import com.revature.controllers.RequestsController;

import io.javalin.Javalin;
import io.javalin.plugin.json.JsonMapper;

public class TRMSApp {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
			config.jsonMapper(new JacksonMapper());
			
		});
		
		app.start();
		
		
		
		app.routes(() -> {
			
			//localhost:8080/employees
			path("/employees", () -> {
				get(EmployeeController::viewAllEmployees);
				
				
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
	}
	
	
}

class JacksonMapper implements JsonMapper {
    ObjectMapper om = new ObjectMapper();

    {
        om.findAndRegisterModules();
    }

    @Override
    public String toJsonString(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public <T> T fromJsonString(String json, Class<T> targetClass) {
        try {
            return om.readValue(json, targetClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
}
package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.EventType;
import com.revature.data.postgres.EventTypePostgres;
import com.revature.utils.DAOFactory;

public class EventTypeDaoTest {

	private EventTypeDAO evt = DAOFactory.getEventTypeDAO();
	
	@BeforeEach
	public void setup() {
		evt = new EventTypePostgres();
		
	}
	
	@Test
	public void getByNameValidEventType() {
		String exp = "University Course";
		EventType et = evt.getByName(exp);
		String actual =et.getName();
		assertEquals(exp, actual);
	}
	
	
	@Test 
	public void getByInvalidNameEventType() {
		String exp = "Uni Cour";
		String real = "University Course";
		EventType et = evt.getByName(real);
		String act = et.getName();
		assertNotEquals(exp, act);
		
	}
	
	@Test
	public void getByNameNotNull() {
		String et = "Other";
		EventType act = evt.getByName(et);
		assertNotEquals(null, act);
		
	}
	
	@Test
	public void getByValidIdEventType() {
		String exp = "Seminar";
		EventType two = evt.getById(2);
		String act = two.getName();
		assertEquals(exp, act);
		
	}
	
	@Test
	public void getByIdNotNull() {
		EventType act = evt.getById(3);
		assertNotEquals(null, act);
		
	}
	
	
	@Test
	public void getAllNotNull() {
		Set<Object> act = evt.getAll();
		assertNotEquals(null, act);
		
	}
	
	
	
	
}

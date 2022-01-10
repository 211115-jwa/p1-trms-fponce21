package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.GradingFormat;
import com.revature.data.postgres.GradingFormatPostgres;
import com.revature.utils.DAOFactory;

public class GradingFormatDaoTest {
	private GradingFormatDAO gradform = DAOFactory.getGradingFormatDAO();
	
	@BeforeEach
	public void setup() {
		gradform = new GradingFormatPostgres();
		
	}
	
	@Test
	public void getByValidNameGradingFormat() {
		String exp ="Pass/Fail";
		GradingFormat gf = gradform.getByName(exp);
		String act = gf.getName();
		assertEquals(exp, act);
		
		
	}
	
	@Test
	public void getByInvalidNameGradingFormat() {
		String exp = "O";
		String real = "Other";
		GradingFormat gf = gradform.getByName(real);
		String act = gf.getName();
		assertNotEquals(exp, act);
	}
	
	@Test
	public void getByNameNotNull() {
		String name = "Percentage";
		GradingFormat act = gradform.getByName(name);
		assertNotEquals(null, act);
		
	}
	
	@Test
	public void getAllNotNull() {
		Set<Object> act = gradform.getAll();
		assertNotEquals(null, act);
		
	}
	
	@Test
	public void getByValidIdGradingFormat() {
		String exp = "Other";
		GradingFormat four = gradform.getById(4);
		String act = four.getName();
		assertEquals(exp, act);
	}
	
	@Test
	public void getByIdNotNull () {
		GradingFormat act = gradform.getById(2);
		assertNotEquals(null, act);
		
	}

}

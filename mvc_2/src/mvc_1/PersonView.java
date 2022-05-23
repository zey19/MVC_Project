package mvc_1;

import java.sql.ResultSet;
import java.util.*;

public class PersonView implements ViewInterface{
	public static class ComparePerson {
	    public static String name;
	    public String password;
	    
	    public static String getName()
	    {
	    	return name;
	    }
	}
	ComparePerson compare = new ComparePerson();
	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		switch(operationName) {
		case "select": return selectOperation(modelData);
		case "select.gui": return selectGUI(modelData); 
		case "insert": return insertOperation(modelData);	
		case "register.gui": return insertGUI(modelData);
		case "login": return loginOperation(modelData);	
		case "login.gui": return loginGUI(modelData);
		}
		
		return new ViewData("MainMenu", "");
	}
	
	Map<String, Object> getWhereParameters() throws Exception {
		System.out.println("Filter conditions:");
		String username = getString("Username : ", false);
		String password = getString("Password : ", false);
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (username != null) whereParameters.put("username", username);
		//if (password != null) whereParameters.put("password", password);

		compare.name = username;
		compare.password = password;
		
		return whereParameters;
	}
	
	ViewData loginGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
	
		return new ViewData("Person", "login", parameters);
	}
	
	ViewData loginOperation(ModelData modelData) throws Exception {
		
		ResultSet resultSet = modelData.resultSet;
		
		if (resultSet != null) {
			if(!resultSet.isBeforeFirst())
			{
				System.out.println("there is no user with this username!");
				return loginGUI(modelData);
			}
			while (resultSet.next()) {
				// Retrieve by column name
				
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				
				/*
				System.out.println("name");
				System.out.println(mark);
				*/
				System.out.println("******");
				System.out.println(compare.name);
				System.out.println(compare.password);
				System.out.println("*******");
				if(compare.name.equals(username) && compare.password.equals(password)) {
					System.out.println("Hereee");
					return new ViewData("MainMenu", "");
				}
				if(! compare.password.equals(password)) {
					System.out.println("Wrong PassWord!!");
					return new ViewData("LoginMenu", "");
				}
				// Display values
				
			}
			resultSet.close();	
		}
		
		return new ViewData("LoginMenu", "");
	}
	
	
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Person", "select", parameters);
	}
	
	ViewData selectOperation(ModelData modelData) throws Exception {
		
		ResultSet resultSet = modelData.resultSet;
		
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				/*
				System.out.println("name");
				System.out.println(mark);
				*/
				System.out.println("******");
				System.out.println(compare.name);
				System.out.println(compare.password);
				System.out.println("*******");
				if(compare.name.equals(username) && compare.password.equals(password)) {
					System.out.println("Hereee");
					return new ViewData("MainMenu", "");
				}
			
				// Display values
				
			}
			resultSet.close();	
		}
		
		return new ViewData("LoginMenu", "");
	}
	
	ViewData insertOperation(ModelData modelData) throws Exception {
		//System.out.println("Number of inserted rows is " + modelData.recordCount);
		if(modelData.recordCount== 0)
		{
			return new ViewData("LoginMenu","");
		}
		
		return new ViewData("MainMenu", "");
	}
	
	ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		
		
			parameters.put("fieldNames", "username, password, name, surname");

			List<Object> rows = new ArrayList<>();
			
			String username, password,name,surname;
			
			{ 
				System.out.println("Fill the info's:");
				username = getString("username : ", false);
				password = getString("password : ", false);
				name = getString("name : ", false);
				surname = getString("surname : ", false);
				System.out.println();
						
				if (username != null && password != null && name != null && surname != null) {
					rows.add(new Person(username, password ,name,surname));
				}
			}
			
			
			parameters.put("rows", rows);
	
		
		
		return new ViewData("Person", "insert", parameters);
	}
	
}

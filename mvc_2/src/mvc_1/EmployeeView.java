package mvc_1;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mvc_1.PersonView.ComparePerson;

public class EmployeeView implements ViewInterface {
	public static class CompareEmployee {
	    public static String ID;
	    public static String password;
	    
	    public static String getID()
	    {
	    	return ID;
	    }
	}
	CompareEmployee compare = new CompareEmployee();
	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		switch(operationName) {
		//case "select": return selectOperation(modelData);
		//case "select.gui": return selectGUI(modelData); 
		case "login.gui": return loginGUI(modelData); 
		case "login": return loginOperation(modelData); 
		case "insert": return insertOperation(modelData);
		case "select": return selectOperation(modelData);
		case "select.gui": return selectGUI(modelData); 
		//case "insert.gui": return insertGUI(modelData);
		
		}
		
		return new ViewData("EmployeeMenu", "");
	}
	
	Map<String, Object> getWhereParameters() throws Exception {
		
		System.out.println("Filter conditions:");
		String employee_id = getString("employee_id: ", false);
		String password = getString("password : ", false);
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (employee_id != null) whereParameters.put("employee_id", employee_id);
		//if (password != null) whereParameters.put("password", password);

		compare.ID = employee_id;
		compare.password = password;
		
		return whereParameters;
	}
	

	ViewData loginGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Employee", "login", parameters);
	}
	
	
	
	ViewData loginOperation(ModelData modelData) throws Exception {
				try {
					
					ResultSet resultSet = modelData.resultSet;
					if (resultSet != null) {
						
						if(!resultSet.isBeforeFirst())
						{
							System.out.println("there is no existing id ");
						return loginGUI(modelData);	
						}
					while (resultSet.next()) {
						// Retrieve by column name
						
						String employee_id = resultSet.getString("employee_id");
						String password = resultSet.getString("password");
						/*
						System.out.println("name");
						System.out.println(mark);
						*/
						System.out.println("******");
						System.out.println(employee_id);
						System.out.println(password);
						System.out.println("*******");
						if(compare.ID.trim().equals(employee_id.trim()) && compare.password.trim().equals(password.trim())) {
							System.out.println("Hereee");
							return new ViewData("EmployeeMenu", "");
						}
							if( ! compare.password.trim().equals(password.trim())) {
							System.out.println("Wrong PassWord");
							return new ViewData("LoginMenu", "");
							
						
						}
						
						}
					resultSet.close();	
				}
				} catch (Exception e) {
					System.out.println("!!! Enter Right Credendiatls !!!");// TODO: handle exception
				}
				return new ViewData("LoginMenu", "");
		} 
		
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Employee", "select", parameters);
	}
	
	ViewData selectOperation(ModelData modelData) throws Exception {
		try {
			ResultSet resultSet = modelData.resultSet;
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				
				String employee_id = resultSet.getString("employee_id");
				String password = resultSet.getString("password");
				/*
				System.out.println("name");
				System.out.println(mark);
				*/
				System.out.println("******");
				System.out.println(compare.ID);
				System.out.println(compare.password);
				System.out.println("*******");
				if(compare.ID.equals(employee_id) && compare.password.equals(password)) {
					System.out.println("Hereee");
					return new ViewData("EmployeeMenu", "");
				}
			
				// Display values
				
			}
			resultSet.close();	
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		return new ViewData("LoginMenu", "");
	}
	ViewData insertOperation(ModelData modelData) throws Exception {
		System.out.println("Number of inserted rows is " + modelData.recordCount);
		
		return new ViewData("EmployeeMenu", "");
	}

}

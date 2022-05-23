package mvc_1;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc_1.EmployeeView.CompareEmployee;
import mvc_1.PersonView.ComparePerson;

public class TaskView implements ViewInterface{
	public static class Check {
	    public static String id;
	   
	    
	 
	public static String getId() {
			return id;
		}

		public static void setId(String id) {
			Check.id = id;
		}
	}
	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		switch(operationName) {
		case "select": return selectOperation(modelData);
		case "select.gui": return selectGUI(modelData);
		case "insert":return insertGUI(modelData);
		case "insert.gui" : return insertGUI(modelData);
		case "delete" : return deleteOperation(modelData);
		case "delete.gui" : return deleteGUI(modelData);
		case "update" : return updateOperation(modelData);
		case "update.gui" : return updateGUI(modelData);
		}
		
		return new ViewData("MainMenu", "");
	}
	
	Map<String, Object> getWhereParameters() throws Exception {
		System.out.println("Filter conditions:");
		
		Map<String, Object> whereParameters = new HashMap<>();
	
			String task_id = getString("task_id : ", false);
			Check.setId(task_id);
		if (task_id != null) whereParameters.put("task_id", task_id);
		//if (password != null) whereParameters.put("password", password);
		
			
		return whereParameters;
		
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		if(getWhereParameters().containsValue("task_id")) {
		parameters.put("whereParameters", getWhereParameters());
		//String check = Check.getId();	
		}
		
		return new ViewData("Task", "select", parameters);
	}
	
	ViewData selectOperation(ModelData modelData) {
		try {
			String name = ComparePerson.getName();
		String check= Check.getId();
		if(name != null || check != null) {
		ResultSet resultSet = modelData.resultSet;
		System.out.println("TaskID \t Task Name\t Employee ID\t Due Date\t Description\t Status\t\t Username");
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				
				String task_id = resultSet.getString(1);
				String task_name = resultSet.getString(2);
				String employee_id = resultSet.getString(3);
				Date due_date = resultSet.getDate("due_date");
				String description = resultSet.getString(5);
				String status = resultSet.getString(6);
				String username = resultSet.getString(7);
				
				System.out.print(task_id+" \t  ");
				System.out.print(task_name+"\t \t");
				System.out.print(employee_id+"\t \t");
				System.out.print(due_date+"\t");
				System.out.print(description+"\t\t");
				System.out.print(status+"\t");
				System.out.print(username+"\t");
				System.out.println("\n");
			
				// Display values
				
			}
			resultSet.close();	
			Check.setId(null);
			return check != null ? new ViewData("EmployeeMenu" , "") :  new ViewData("Person" , "");
		}
		}
		else {
			ResultSet resultSet = modelData.resultSet;
			System.out.println("TaskID \t Task Name\t Employee ID\t Due Date\t Description\n ");
			if (resultSet != null) {
				while (resultSet.next()) {
					// Retrieve by column name
					
					String task_id = resultSet.getString(1);
					String task_name = resultSet.getString(2);
					String employee_id = resultSet.getString(3);
					Date due_date = resultSet.getDate("due_date");
					String description = resultSet.getString(5);
					
					System.out.print(task_id+" \t  ");
					System.out.print(task_name+"\t \t");
					System.out.print(employee_id+"\t \t");
					System.out.print(due_date+"\t");
					System.out.print(description+"\t\t");
					System.out.println("\n");
				
					// Display values
					
				}
				resultSet.close();
				
			}
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ViewData("EmployeeMenu", "");
	}
	
	
	ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		
		int compare_counter =0 ;
		
		parameters.put("fieldNames", "task_id, task_name, employee_id, due_date, description");

		List<Object> rows = new ArrayList<>();
		
		String task_id,task_name,employee_id,due_date,description;
		
			compare_counter++;
			System.out.println("Fill the info's:");
			task_id = getString("task_id : ", true);
			task_name = getString("task_name : ", true);
			employee_id =CompareEmployee.getID();
			due_date = getString("due_date : ",true);
			description = getString("description : ", true);
			System.out.println();
					
			if (task_id != null && task_name != null && due_date != null &&  description != null) {
				
				rows.add(new Task(task_id, task_name ,employee_id ,due_date, description));
			}
			else if(task_id == null && task_name == null  && due_date == null &&  description == null) {
				
				compare_counter--;
				return new ViewData("EmployeeMenu", "");				
			}
		
			else if(compare_counter != modelData.recordCount) {
			
				return new ViewData("Task", "insert", parameters);	
			}
		
		
		
		parameters.put("rows", rows);

	
	
	return new ViewData("Task", "insert", parameters);
	}
	ViewData deleteOperation(ModelData modelData) throws Exception {
		System.out.println("Number of deleted rows is " + modelData.recordCount);
		
		return new ViewData("EmployeeMenu", "");
	}	
	
	ViewData deleteGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParametersForDelete());
		
		return new ViewData("Task", "delete", parameters);
	}
	
	Map<String, Object> getWhereParametersForDelete() throws Exception {
		System.out.println("Filter conditions:");
		String task_id = getString("Task ID : ", false);
		
		
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (task_id != null) whereParameters.put("task_id", task_id);
		//if (password != null) whereParameters.put("password", password);
		
		return whereParameters;
	}
	

	
	ViewData updateGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		Map<String, Object> updateParameters = new HashMap<>();
		try {
			

		parameters.put("whereParameters", getWhereParametersForUpdate());
		String name = ComparePerson.getName();
		if(name!= null) {
			System.out.println("Fields to update:");
			System.out.println("ASSIGNED - FAILED - COMPLETED - IN PROGRESS - POSTPONED ");
			String status = getString("status : ", false);
			
			if (status != null) updateParameters.put("status", status.toUpperCase());
		}
		else {
			System.out.println("Fields to update:");
		String task_name = getString("task_name : ", false);
		String due_date = getString("due_date : ", false);
		String description = getString("description : ", false);
		System.out.println();
		
		if (due_date != null) updateParameters.put("due_date", due_date);
		if (task_name != null) updateParameters.put("task_name", task_name);
		if (description != null) updateParameters.put("description", description);
		}
		
		parameters.put("updateParameters", updateParameters);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return new ViewData("Task", "update", parameters);
	}
	Map<String, Object> getWhereParametersForUpdate() throws Exception {
		System.out.println("Filter conditions:");
		//String status = getString("status : ", false);
		
		String task_id= getString("task_id : ", true);
		
		Map<String, Object> whereParameters = new HashMap<>();
		//if (status != null) whereParameters.put("status", status);
		
		//if (password != null) whereParameters.put("password", password);
		if (task_id != null) whereParameters.put("task_id", task_id);
		return whereParameters;
	}
	ViewData updateOperation(ModelData modelData) throws Exception {
		System.out.println("Number of updated rows is " + modelData.recordCount);
		String name = ComparePerson.getName();
		if(modelData.recordCount==0)
			System.out.println("Cannot find assigned task with given ID. \n Please enter existing assigned task ID");
		if(name!=null)
			return new ViewData("Person", "");
		return new ViewData("EmployeeMenu", "");
	}
}

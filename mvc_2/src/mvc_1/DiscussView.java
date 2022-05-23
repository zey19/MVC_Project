package mvc_1;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc_1.PersonView.ComparePerson;

public class DiscussView implements ViewInterface{
	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		switch(operationName) {
		
		case "select" : return selectOperation(modelData);
		case "select.gui" : return selectGUI(modelData);
		case "insert":return insertOperation(modelData);
		case "insert.gui" : return insertGUI(modelData);
		
		}
		
		return new ViewData("MainMenu", "");
	}
	
	ViewData selectOperation(ModelData modelData)  {
		try {
			System.out.println("Date \t\t\tUsername \tDiscussion \t\n");
		
		ResultSet resultSet = modelData.resultSet;
		
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				//String task_id = resultSet.getString("task_id");
				String discussion = resultSet.getString("discussion");
				String username = resultSet.getString("username");
				String date = resultSet.getString("date");
				
				
				//String groupName = resultSet.getString("GroupName");
				//Date modifiedDate = resultSet.getDate("ModifiedDate");
				
				// Display values
				//System.out.print("task_id:  "+task_id + "\n");
				
				System.out.print(date + "\t");
				System.out.print(username + "\t");
				System.out.print(discussion + "\n");
				//System.out.println(modifiedDate);
			}
			resultSet.close();	
		}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return ComparePerson.getName() != null ? new ViewData("MainMenu","") :new ViewData("EmployeeMenu","")  ;
	}
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Discuss", "select", parameters);
	}
	
	Map<String, Object> getWhereParameters() throws Exception {
		System.out.println("Filter conditions:");
		
		Map<String, Object> whereParameters = new HashMap<>();
	
			String task_id = getString("task_id : ", false);
		
		if (task_id != null) whereParameters.put("task_id", task_id);
		//if (password != null) whereParameters.put("password", password);
		
		
			
		return whereParameters;
		
	}
	
	ViewData insertGUI(ModelData modelData) throws Exception {
	
		Map<String, Object> parameters = new HashMap<>();
		
		int compare_counter =0 ;
		
		parameters.put("fieldNames", "task_id, discussion,username,date");

		List<Object> rows = new ArrayList<>();
		
		String task_id,discussion,username,date;
	
			compare_counter++;
			System.out.println("Fill the info's:");
			task_id = getString("task_id : ",true );
			discussion = getString("discussion : ",true );
			username = ComparePerson.getName();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();	
			date = now.format(dtf);
			System.out.println();
					
			if (task_id != null && username != null && discussion!=null && date!= null ) {
				
				rows.add(new Discuss(task_id, discussion, username, date));
			}
			/*
			else if(task_id == null && person_name == null ) {
				
				compare_counter--;
				return new ViewData("EmployeeMenu", "");				
			}
		
			else if(compare_counter != modelData.recordCount) {
			
				return new ViewData("AssignedTask", "insert", parameters);	
			}
		*/
	
		
		parameters.put("rows", rows);
		return new ViewData("Discuss", "insert", parameters);
	}
	
	ViewData insertOperation(ModelData modelData) throws Exception {
		System.out.println("Number of inserted rows is " + modelData.recordCount);
		
		
		return new ViewData("Person", "");
	}

}

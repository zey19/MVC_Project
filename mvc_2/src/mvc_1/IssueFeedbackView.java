package mvc_1;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc_1.PersonView.ComparePerson;

public class IssueFeedbackView implements ViewInterface {
	
	public String issues() throws ParseException {
		String issue_id;
		
		
		System.out.println("******ISSUES******");
		System.out.println("1. Lack of Description");
		System.out.println("2. Invalid Documents");
		System.out.println("3. Unsufficient Time");
		System.out.println("*******************");
		do {
			issue_id= getString("issue_id:", false);
			
		}while( Integer.parseInt(issue_id) <1 ||  Integer.parseInt(issue_id) >3 || issue_id == null);
		
		return issue_id;
	}
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
	
	ViewData selectOperation(ModelData modelData) throws Exception {
		
		System.out.println("******ISSUES****** \n");
		
		ResultSet resultSet = modelData.resultSet;
		
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				//String task_id = resultSet.getString("task_id");
				String task_id = resultSet.getString("task_id");
				String username = resultSet.getString("username");
				String definition = resultSet.getString("definition");
				
				System.out.print(task_id + "\t");
				System.out.print(username + "\t");
				System.out.print(definition + "\t");
				System.out.println("\n");
				
				
			}
			resultSet.close();	
		}
		
		return ComparePerson.getName() != null ? new ViewData("MainMenu","") :new ViewData("EmployeeMenu","")  ;
	}
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		
		parameters.put("whereParameters", getWhereParameters());
	
		return new ViewData("IssueFeedback", "select", parameters);
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
		
		
		
		parameters.put("fieldNames", "issue_id,task_id,username");

		List<Object> rows = new ArrayList<>();
		
		String issue_id,task_id,username;
			
			
			task_id = getString("task_id : ",true );
			username = ComparePerson.getName();
			issue_id = issues();
			System.out.println();
					
			if (task_id != null && username != null && issue_id!=null ) {
				
				rows.add(new IssueFeedback(task_id,issue_id, username));
			}
			
	
		
		parameters.put("rows", rows);
		return new ViewData("IssueFeedback", "insert", parameters);
	}
	
	ViewData insertOperation(ModelData modelData) throws Exception {
		System.out.println("Number of inserted rows is " + modelData.recordCount);
		
		
		return new ViewData("MainMenu", "");
	}
}

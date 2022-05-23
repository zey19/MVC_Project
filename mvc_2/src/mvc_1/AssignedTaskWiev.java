package mvc_1;
import java.sql.Date;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import mvc_1.EmployeeView.CompareEmployee;
	import mvc_1.PersonView.ComparePerson;
public class AssignedTaskWiev implements ViewInterface{
	
		@Override
		public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
			switch(operationName) {
		
			case "insert":return insertOperation(modelData);
			case "insert.gui" : return insertGUI(modelData);
			case "update":return updateOperation(modelData);
			case "update.gui" : return updateGUI(modelData);
			}
			
			return new ViewData("MainMenu", "");
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
			
			parameters.put("fieldNames", "task_id, username");

			List<Object> rows = new ArrayList<>();
			
			String task_id,username;
		do {
				compare_counter++;
				System.out.println("Fill the info's:");
				task_id = getString("task_id : ",true );
				username = getString("username : ", true);		
				
				System.out.println();
						
				if (task_id != null && username != null ) {
					
					rows.add(new AssignedTask(task_id, username));
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
		}while(task_id != null && username != null);
			
			parameters.put("rows", rows);
			return new ViewData("AssignedTask", "insert", parameters);
		}
		
		ViewData insertOperation(ModelData modelData) throws Exception {
			System.out.println("Number of inserted rows is " + modelData.recordCount);
			
			
			return new ViewData("EmployeeMenu", "");
		}


		ViewData updateGUI(ModelData modelData) throws Exception {
				Map<String, Object> parameters = new HashMap<>();
			Map<String, Object> updateParameters = new HashMap<>();

			try {
			
			parameters.put("whereParameters", getWhereParametersForUpdate());
		
			System.out.println("Fields to update:");
			System.out.println("ASSIGNED - FAILED - COMPLETED - IN PROGRESS - POSTPONED ");
			String status = getString("status : ", false);
				
			if (status != null) updateParameters.put("status", status.toUpperCase());
			parameters.put("updateParameters", updateParameters);
			
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return new ViewData("AssignedTask", "update", parameters);
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
			
			if(modelData.recordCount==0)
				System.out.println("Cannot find assigned task with given ID. \n Please enter existing assigned task ID");
			
				return new ViewData("MainMenu", "");
			
		}
		
	}


package mvc_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mvc_1.EmployeeView.CompareEmployee;
import mvc_1.PersonView.ComparePerson;
import mvc_1.TaskView.Check;

public class TaskModel implements ModelInterface {

	@Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		String name = ComparePerson.getName();
		
		if(name != null) {
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("	* ");
		sql.append(" FROM assigned_task where username='"+name+"'" );
		
		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
		ResultSet result = preparedStatement.executeQuery();
		ArrayList<String> arr = new ArrayList<>();
		if(result != null) {
			while (result.next()) {
				// Retrieve by column name
				
				String task_id = result.getString(1);
				arr.add(task_id);
				
			}
			result.close();	
		}
		String ids = "";
		for(int i = 0;i<arr.size();i++) {
			if(i != arr.size()-1)
				ids+= "t.task_id="+arr.get(i)+" or ";
			else
				ids+= "t.task_id="+arr.get(i);
		}
		//System.out.println(ids);
		StringBuilder sql2 = new StringBuilder();
		sql2.append("SELECT \r\n"
				+ " t.task_id\r\n"
				+ " ,task_name\r\n"
				+ " ,employee_id\r\n"
				+ " ,due_date\r\n"
				+ " ,description\r\n"
				+ " ,ast.status\r\n"
				+ " ,ast.username\r\n"
				+ "FROM task t INNER JOIN assigned_task as ast ON ast.task_id = t.task_id");
		
		sql2.append("  where "+ids+"" );
		
		//System.out.println(sql2);
		List<Map.Entry<String, Object>> whereParameterList2 = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql2.append(DatabaseUtilities.prepareWhereStatement(whereParameterList2));
		
		// execute constructed SQL statement
		Connection connection2 = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement2 = connection2.prepareStatement(sql2.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement2, whereParameterList2);
		ResultSet result2 = preparedStatement2.executeQuery();
				
		
		return result2;
		}
		else {
		String check = Check.getId();
		StringBuilder sql2 = new StringBuilder();
		
		 if(check != null ) {
			
			sql2.append("SELECT \r\n"
					+ " t.task_id\r\n"
					+ " ,task_name\r\n"
					+ " ,employee_id\r\n"
					+ " ,due_date\r\n"
					+ " ,description\r\n"
					+ " ,ast.status\r\n"
					+ " ,ast.username\r\n"
					+ "FROM task t INNER JOIN assigned_task as ast ON ast.task_id = t.task_id");
			
			sql2.append("  where t.task_id = "+check+"" );
			
			
			}
			else if (check == null)
			{
			sql2.append("SELECT * FROM task");
			
			}
			
		
		
		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
			sql2.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
			
			// execute constructed SQL statement
			Connection connection = DatabaseUtilities.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql2.toString());
			DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
			ResultSet result2 = preparedStatement.executeQuery();
			return result2;
		
		
		}
		
		
		
	}
	
	public int insert(String fieldNames, List<Object> rows) throws Exception {
		// construct SQL statement
		int rowCount = 0;
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO task (" + fieldNames + ") " );
			sql.append(" VALUES ");

			String[] fieldList = fieldNames.split(",");

			for (int i=0; i<rows.size(); i++) {
				if (rows.get(i) instanceof Task) {
					rowCount++;
					
					Task task = (Task)rows.get(i); 
		
					sql.append("(");
					for (int j=0; j<fieldList.length; j++) {
						String fieldName = fieldList[j].trim();
						sql.append(DatabaseUtilities.formatField(task.getByName(fieldName)));
						if (j < fieldList.length - 1) {
							sql.append(", ");
						}
					}
					sql.append(")");
					
					if (i < rows.size() - 1) {
						sql.append(", ");
					}				
				}
			}		
			//System.out.println(sql.toString());
			
			// execute constructed SQL statement
			if (rowCount > 0) {
				Connection connection = DatabaseUtilities.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
				rowCount = preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			
			
			
		} catch (Exception e) {
			rowCount--;
			System.out.println(e);// TODO: handle exception
			System.out.println("Enter valid values");
		}
		
		
		return rowCount;
	}
/*	@Override
	public int insert(String fieldNames, List<Object> rows) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	

	@Override
	public int delete(Map<String,Object> whereParameters) throws Exception
	{	int rowCount =0;
		try {
			// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM task ");

		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		//System.out.println(sql.toString());

	
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);		
		 rowCount = preparedStatement.executeUpdate();
		preparedStatement.close();
		} catch (Exception e) {
			rowCount--;
			System.out.println(e);
		}
		if (rowCount==0)
			System.out.println("cannot find id ! please enter existing id");
		
		return rowCount;
	}
	
	@Override
	public int update(Map<String,Object> updateParameters, Map<String,Object> whereParameters) throws Exception
	{	String name= ComparePerson.getName();
		int rowCount=0;
		
	try {
		
		if(name!=null) {
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE task SET ");
			int appendCount = 0;
			for (Map.Entry<String, Object> entry : updateParameters.entrySet()) {
				sql.append(entry.getKey() + " = " + DatabaseUtilities.formatField(entry.getValue()));
				if (++appendCount < updateParameters.size()) {
					sql.append(", ");
				}
			}
			List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
			sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
			//System.out.println(sql.toString());
			
		
			// execute constructed SQL statement
			Connection connection = DatabaseUtilities.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
			rowCount = preparedStatement.executeUpdate(); 	
			
			preparedStatement.close();
		}
		
		else {
			StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE task SET ");
		int appendCount = 0;
		for (Map.Entry<String, Object> entry : updateParameters.entrySet()) {
			sql.append(entry.getKey() + " = " + DatabaseUtilities.formatField(entry.getValue()));
			if (++appendCount < updateParameters.size()) {
				sql.append(", ");
			}
		}
		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		//System.out.println(sql.toString());
		
	
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
		rowCount = preparedStatement.executeUpdate(); 	
		
		preparedStatement.close();
		}
		
		
		
		
	} catch (Exception e) {
		rowCount--;
		System.out.println(e);
	}
		// construct SQL statement
		return rowCount;
	}

}

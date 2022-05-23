package mvc_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mvc_1.PersonView.ComparePerson;
import mvc_1.TaskView.Check;

public class AssignedTaskModel implements ModelInterface {
	@Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		return null;
		}
	
	
	public int insert(String fieldNames, List<Object> rows) throws Exception {
		// construct SQL statement
		int rowCount = 0;
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO assigned_task (" + fieldNames + ") " );
			sql.append(" VALUES ");

			String[] fieldList = fieldNames.split(",");

			for (int i=0; i<rows.size(); i++) {
				if (rows.get(i) instanceof AssignedTask) {
					rowCount++;
					
					AssignedTask task = (AssignedTask)rows.get(i); 
		
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
	{
		return 0;
	}
	
	@Override
	public int update(Map<String,Object> updateParameters, Map<String,Object> whereParameters) throws Exception
	{	
		int rowCount=0;
		
	try {
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE assigned_task SET ");
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
		
		
	} catch (Exception e) {
		rowCount--;
		System.out.println(e);
	}
		// construct SQL statement
		return rowCount;
	}
}

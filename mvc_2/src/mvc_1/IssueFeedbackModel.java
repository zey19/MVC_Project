package mvc_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class IssueFeedbackModel implements ModelInterface {

	@Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		// construct SQL statement
		ResultSet result = null;
		try {
			StringBuilder sql = new StringBuilder();
				sql.append(" SELECT ");
				sql.append("t.task_id\r\n"
						+ ",t.username\r\n"
						+ ",t2.definition");
				sql.append(" FROM issue_feedback as t INNER JOIN issue as t2 ON t.issue_id=t2.issue_id ");

				List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
				sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
				
				//sql.append("ORDER BY date");		
				//System.out.println(sql.toString() + "\n");

				
				// execute constructed SQL statement
				Connection connection = DatabaseUtilities.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
				//DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
				 result = preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
				
				
				return result;
	}

	@Override
	public int insert(String fieldNames, List<Object> rows) throws Exception {
		int rowCount =0;
		try {
			StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO issue_feedback (" + fieldNames + ") " );
		sql.append(" VALUES ");

		String[] fieldList = fieldNames.split(",");

	
		for (int i=0; i<rows.size(); i++) {
			if (rows.get(i) instanceof IssueFeedback) {
				rowCount++;
				
				IssueFeedback issueFeedback = (IssueFeedback)rows.get(i); 
	
				sql.append("(");
				for (int j=0; j<fieldList.length; j++) {
					String fieldName = fieldList[j].trim();
					sql.append(DatabaseUtilities.formatField(issueFeedback.getByName(fieldName)));
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
			System.out.println(e);
			rowCount--;
		}
		
		
		return rowCount;
		
	}

	@Override
	public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Map<String, Object> whereParameters) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}

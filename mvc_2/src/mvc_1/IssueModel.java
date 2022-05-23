package mvc_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class IssueModel implements ModelInterface{

	@Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		// construct SQL statement
		ResultSet result = null;
		try {
			StringBuilder sql = new StringBuilder();
				sql.append(" SELECT * ");
				
				sql.append(" FROM  issue  ");

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
		// TODO Auto-generated method stub
		return 0;
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

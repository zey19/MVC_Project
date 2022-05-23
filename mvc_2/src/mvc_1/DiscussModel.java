package mvc_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

 class DiscussModel implements ModelInterface{
	
	
	@Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		ResultSet result = null;
		try {
				// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("* ");
		sql.append(" FROM discuss ");

		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		
		sql.append("ORDER BY date");		
		//System.out.println(sql.toString() + "\n");

		
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
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
			// construct SQL statement
				StringBuilder sql = new StringBuilder();
				sql.append(" INSERT INTO discuss (" + fieldNames + ") " );
				sql.append(" VALUES ");

				String[] fieldList = fieldNames.split(",");

				 rowCount = 0;
				for (int i=0; i<rows.size(); i++) {
					if (rows.get(i) instanceof Discuss) {
						rowCount++;
						
						Discuss discuss = (Discuss)rows.get(i); 
			
						sql.append("(");
						for (int j=0; j<fieldList.length; j++) {
							String fieldName = fieldList[j].trim();
							sql.append(DatabaseUtilities.formatField(discuss.getByName(fieldName)));
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

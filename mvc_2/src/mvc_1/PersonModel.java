package mvc_1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class PersonModel implements ModelInterface {
	
	public int insert(String fieldNames, List<Object> rows) throws Exception {
		// construct SQL statement
		int rowCount = 0;
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO person (" + fieldNames + ") " );
			sql.append(" VALUES ");

			String[] fieldList = fieldNames.split(",");

			for (int i=0; i<rows.size(); i++) {
				if (rows.get(i) instanceof Person) {
					rowCount++;
					
					Person person = (Person)rows.get(i); 
		
					sql.append("(");
					for (int j=0; j<fieldList.length; j++) {
						String fieldName = fieldList[j].trim();
						sql.append(DatabaseUtilities.formatField(person.getByName(fieldName)));
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
			System.out.println("Please enter valid username and password!");// TODO: handle exception
		}
		
		
		return rowCount;
	}

	@Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("	* ");
		sql.append(" FROM person ");

		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		
		//sql.append("ORDER BY DepartmentID");		
		//System.out.println(sql.toString() + "\n");

		
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
		ResultSet result = preparedStatement.executeQuery();
		
		return result;
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

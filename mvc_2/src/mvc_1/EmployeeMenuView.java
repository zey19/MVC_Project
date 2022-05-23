package mvc_1;

import java.util.HashMap;
import java.util.Map;

public class EmployeeMenuView implements ViewInterface{
	
	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice;
		do {
			System.out.println("1. Show all tasks");
			System.out.println("2. Show tasks");
			System.out.println("3. Add a task");
			System.out.println("4. Assign A task");
			System.out.println("5. Update a task");
			System.out.println("6. Delete a task");
			System.out.println("7. Get Discussions");
			System.out.println("8. Show Issues");
			System.out.println("9. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 9);
		
		
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("mainMenuChoice", choice);
		
		switch (choice.intValue()) {
		case 1: operationName = "select";		break;
		case 2: operationName = "select.gui";	break;
		case 3: operationName = "insert.gui";	break;
		case 4: operationName = "insert.gui";	return new ViewData("AssignedTask", operationName, new HashMap<>()); 
		case 5: operationName = "update.gui";	break;
		case 6: operationName = "delete.gui";	break;
		case 7: operationName = "select.gui";	return new ViewData("Discuss", operationName, new HashMap<>());
		case 8: operationName = "select";	return new ViewData("IssueFeedback", operationName, new HashMap<>());
		default: return new ViewData(null, null);
		}
		
		return new ViewData("Task", operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "Main Menu View";
	}	
	
}

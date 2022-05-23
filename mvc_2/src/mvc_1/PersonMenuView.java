package mvc_1;
import java.util.HashMap;
import java.util.Map;

class PersonMenuView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice;
		do {
			System.out.println("1. Show all tasks");
			System.out.println("2. Update a task status");
			System.out.println("3. Send Issue");
			System.out.println("4. Send Discussion");
			System.out.println("5. Get Discussions");
			System.out.println("6. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 6);
		
		
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("mainMenuChoice", choice);
		
		switch (choice.intValue()) {
		case 1: operationName = "select";		break;
		case 2: operationName = "update.gui";	return new ViewData("AssignedTask", operationName, new HashMap<>());
		case 3: operationName = "insert.gui";	return  new ViewData("IssueFeedback", operationName, new HashMap<>());
		case 4: operationName = "insert.gui";	return new ViewData("Discuss", operationName, new HashMap<>());
		case 5: operationName = "select.gui";	return new ViewData("Discuss", operationName, new HashMap<>());
		default: return new ViewData(null, null);
		}
		
		return new ViewData("Task", operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "Main Menu View";
	}		
}

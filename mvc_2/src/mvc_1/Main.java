package mvc_1;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// Connect to database
		connectToDatabase();

		// Model View Controller (MVC)
		// Router knows all the controllers
		Map<String, Controller> router = new HashMap<>();		
		router.put("LoginMenu", new Controller(new LoginMenuView(), new NopModel()));
		router.put("MainMenu", new Controller(new PersonMenuView(), new PersonModel()));
		router.put("Person", new Controller(new PersonView(), new PersonModel()));
		router.put("Employee", new Controller(new EmployeeView(), new EmployeeModel()));
		router.put("EmployeeMenu", new Controller(new EmployeeMenuView(), new EmployeeModel()));
		router.put("Task", new Controller(new TaskView(), new TaskModel()));
		router.put("AssignedTask", new Controller (new AssignedTaskWiev(),new AssignedTaskModel()));
		router.put("Discuss", new Controller(new DiscussView(), new DiscussModel()));
		router.put("IssueFeedback", new Controller(new IssueFeedbackView(), new IssueFeedbackModel()));
		//router.put("Issue", new Controller(new IssueFeedbackView(), new IssueModel()));
		ViewData viewData = new ViewData("LoginMenu", "");	
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);
		
		System.out.println();
		System.out.println();
		System.out.println("Program is ended...");


		// Disconnect from database
		disconnectFromDatabase();
	}

	
	public static void connectToDatabase() {
		DatabaseUtilities.host = "localhost:1433";
		DatabaseUtilities.databaseName = "employee";
		
		try {
			DatabaseUtilities.getConnection();
		}
		catch(Exception e) {
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() {
		try {
			DatabaseUtilities.disconnect();
		}
		catch(Exception e) {
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
	
}

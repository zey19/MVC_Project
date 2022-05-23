package mvc_1;

import java.util.HashMap;
import java.util.Map;

public class LoginMenuView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		Integer choice;
		do {
			System.out.println("1.Employee Login");
			System.out.println("2.Person Login");
			System.out.println("3.Person Register");
			System.out.println("4. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		}
		while (choice == null || choice < 1 || choice > 5);
		
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("loginMenuChoice", choice);
		
		
		switch (choice.intValue()) {
		
		case 1: return new ViewData("Employee", "login.gui",new HashMap<>());
		case 2: return new ViewData("Person", "login.gui", new HashMap<>());
		case 3: return new ViewData("Person", "register.gui", new HashMap<>());
/*		
		{
				System.out.println("User Login:");
				name1 = getString("Name : ", false);
				pw1 = getString("Password : ", false);
				
				if(name1.equals(name) && pw1.equals(pw)) {
					return new ViewData("MainMenu", "");
				}
				else if(name1.equals("admin") && pw1.equals("admin")) {
					return new ViewData("EmployeeMenu", null);
				}
				else {
					name1 = null;pw1=null;
					System.out.println("!!Wrong credentials!!");
					return new ViewData("LoginMenu", null);
				}

			
		}
		*/
		}
	    return new ViewData("MainMenu", operationName, new HashMap<>());
		
		
	}

}

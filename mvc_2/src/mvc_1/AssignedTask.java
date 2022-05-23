package mvc_1;

public class AssignedTask {
	private String task_id;
	private String username;
	
	
	AssignedTask(){
		
	}
	AssignedTask(String task_id,String username){
		this.task_id = task_id;
		this.username=username;
		
	}
	
	public Object getByName(String attributeName) {
		switch (attributeName) {
		case "task_id": return task_id;
		case "username": return username;
		
		default: return null;
		}
	}
}

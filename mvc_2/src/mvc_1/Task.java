package mvc_1;

public class Task {
	private String task_id;
	private String task_name;
	private String employee_id;
	private String due_date;
	private String description;
	
	Task(){
		
	}
	Task(String task_id,String task_name,String employee_id,String due_date,String description){
		this.task_id = task_id;
		this.task_name=task_name;
		this.employee_id=employee_id;
		this.due_date=due_date;
		this.description=description;
	}
	
	public Object getByName(String attributeName) {
		switch (attributeName) {
		case "task_id": return task_id;
		case "task_name": return task_name;
		case "employee_id": return employee_id;
		case "due_date": return due_date;
		case "description": return description;
		default: return null;
		}
	}
	
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

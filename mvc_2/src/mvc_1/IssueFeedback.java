package mvc_1;

public class IssueFeedback {
	private String task_id;
	private String issue_id;
	private String username; 
	public IssueFeedback(String task_id, String issue_id, String username) {
		super();
		this.task_id = task_id;
		this.issue_id = issue_id;
		this.username=username;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}
	
	public Object getByName(String attributeName) {
		switch (attributeName) {
		case "task_id": return task_id;
		case "issue_id": return issue_id;
		case "username": return username;
		
		default: return null;
		}
	}
}

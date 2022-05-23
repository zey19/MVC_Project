package mvc_1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Discuss {
	private String task_id;
	private String discussion;
	private String username;
	private String date;
	
	
	public Discuss(String task_id, String discussion, String username, String date) {
		super();
		this.task_id = task_id;
		this.discussion = discussion;
		this.username = username;
		this.date=date;
	}
	
	
	public Object getByName(String attributeName) {
		switch (attributeName) {
		case "task_id": return task_id;
		case "discussion": return discussion;
		case "username": return username;
		case "date": return date;
		default: return null;
		}
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getDiscussion() {
		return discussion;
	}
	public void setDiscussion(String discussion) {
		this.discussion = discussion;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}

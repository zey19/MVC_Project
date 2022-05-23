package mvc_1;

public class Issue {
	private String issue_id;
	private String definition;
	public Issue(String issue_id, String definition) {
		super();
		this.issue_id = issue_id;
		this.definition = definition;
	}
	
	public Object getByName(String attributeName) {
		switch (attributeName) {
		case "issue_id": return issue_id;
		case "definition": return definition;
		
		default: return null;
		}
	}
	
	
	
	
	
}

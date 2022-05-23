package mvc_1;

public class Person {
	private String username;
	private String password;
	private String name;
	private String surname;
	
	Person(){
		
	}
	
	Person(String username,String password,String name,String surname){
		this.setUsername(username);
		this.setPassword(password);
		this.name = name;
		this.setSurname(surname);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Object getByName(String attributeName) {
		switch (attributeName) {
		case "username": return username;
		case "password": return password;
		case "name": return name;
		case "surname": return surname;
		default: return null;
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		return "";
	}

}
package models;

public abstract class Workers {
	private String name;
	private String lastName;
	private String id;
	private User auth;
	
	public Workers(String name, String lastName, String id, User auth) {
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		this.auth = auth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getAuth() {
		return auth;
	}

	public void setAuth(User auth) {
		this.auth = auth;
	}
}

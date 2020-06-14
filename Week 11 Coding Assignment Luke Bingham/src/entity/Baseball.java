package entity;

public class Baseball {

	private int id;
	private String location;
	private String name;
	
	public Baseball(int id, String location, String name) {
		this.setId(id);
		this.setLocation(location);
		this.setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

	


package cdStore;

public class Users {
	int id;
	String name;
	int money;
	public Users(){};
	public Users(int id, String name, int money) {
		this.id = id;
		this.name = name;
		this.money = money;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", money=" + money + "]";
	}
	
}

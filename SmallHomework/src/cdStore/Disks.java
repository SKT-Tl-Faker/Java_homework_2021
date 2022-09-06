package cdStore;

public class Disks {
	int id;
	int price;
	String name;
	public Disks(){};
	public Disks(int id, int price, String name) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Disks [id=" + id + ", price=" + price + ", name=" + name + "]";
	}
	
}

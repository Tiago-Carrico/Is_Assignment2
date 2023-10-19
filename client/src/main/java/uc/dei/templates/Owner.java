package main.java.uc.dei.templates;

public class Owner {
    
    private int id;
    private String name;
    private int phone;
    private Arraylist<Pet> pets;

    public Owner(int id, String name, int phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
        pets = new ArrayList<Pet>();
    }


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

    public ArrayList<Pet> getPets(){
        return pets;
    }

    public void setPets(ArrayList<Pet> petList){
        this.pets = petList;
    }

    public void addPet(Pet newPet){
        this.pets.add(newPet);
    }

}

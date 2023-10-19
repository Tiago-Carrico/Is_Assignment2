package main.java.uc.dei.templates;

/**
 * Used for Data Transfer (DT) from the server to the client
 */
public class OwnerDTO {
    
    private String name;
    private int phone;
    private Arraylist<Pet> pets;

    public Owner( String name, int phone){
        this.name = name;
        this.phone = phone;
        pets = new ArrayList<Pet>();
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

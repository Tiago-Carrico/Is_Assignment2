package uc.dei.templates;

import java.util.ArrayList;

public class Owner {
    
    private Long id;
    private String name;
    private int phone;
    private ArrayList<Pet> pets;    //TODO should we really keep it like this? doesnt match up with SQL standard way of doing it I think

    public Owner(Long id, String name, int phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
        pets = new ArrayList<Pet>();
    }


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

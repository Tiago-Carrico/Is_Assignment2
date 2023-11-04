package uc.dei.templates;

import java.time.LocalDate;

public class Pet {
    
    private Long id;
    private String name;
    private String species;
    private LocalDate birthDate;
    private float weight;
    private Long ownerId;

	public Pet(){}

    public Pet(Long id, String name, String species, LocalDate birthDate, float weight, Long ownerId){
        this.id = id;
        this.name = name;
        this.species = species;
        this.birthDate = birthDate;
        this.weight = weight;
        this.ownerId = ownerId;
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

	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

    public Long getOwnerId(){
        return ownerId;
    }

    public void setOwner(Long ownerId){
        this.ownerId = ownerId;
    }

	//TODO dont know if after we'll need to also print the owner according to the ownerId but thats a concern for later
	public String toString(){
		return "Pet\nname: " + name + "\nspecies: " + species + "\nbirth date: " + birthDate + "\nweight: " + weight;
	}

}

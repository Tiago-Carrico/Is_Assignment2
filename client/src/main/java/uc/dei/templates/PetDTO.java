package uc.dei.templates;

import java.time.LocalDate;

public class PetDTO {

    private String name;
    private String species;
    private LocalDate birthDate;
    private float weight;
    private Long ownerId;

    public PetDTO(){}

    public void PetDTO(String name, String species, LocalDate birthDate, float weight, Long ownerId){
        this.name = name;
        this.species = species;
        this.birthDate = birthDate;
        this.weight = weight;
        this.ownerId = ownerId;
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getSpecies(){
        return this.species;
    }

    public void setSpecies(String species){
        this.species = species;
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

    public String toString(){
		return "Pet\nname: " + name + "\nspecies: " + species + "\nbirth date: " + birthDate + "\nweight: " + weight + "kg";
	}
}

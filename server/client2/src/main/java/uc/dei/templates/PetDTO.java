package uc.dei.templates;

import java.time.LocalDate;

public class PetDTO {

    private String name;
    private String species;
    private LocalDate birthDate;
    private float weight;
    private Long ownerId;

    public void Pet(String name, String species, LocalDate birthDate, float weight, Long ownerId){
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
}

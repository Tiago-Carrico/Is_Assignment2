package main.java.uc.dei.templates;

public class PetDTO {

    private String name;
    private String species;
    private LocalDate birthDate;
    private float weight;
    private Owner owner;

    public Pet(String name, String species, LocalDate birthDate, float weight, Owner owner){
        this.name = name;
        this.species = species;
        this.birthDate = birthDate;
        this.weight = weight;
        this.owner = owner;
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

    public Owner getOwner(){
        return owner;
    }

    public void setOwner(Owner owner){
        this.owner = owner;
    }
}

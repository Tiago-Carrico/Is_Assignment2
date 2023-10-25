package uc.dei.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("pet")
public class Pet {
    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("species")
    private String species;

    @Column("birthdate")
    private LocalDate birthDate;

    @Column("weight")
    private float weight;

    @Column("owner_id")//TODO also subject to not being like this? seems weird when by default it asks for one owner but what is kept is the owner ID 
    private Owner owner;

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

}

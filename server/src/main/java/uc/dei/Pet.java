package uc.dei;

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

    @Column("owner_id")
    private Long ownerId;

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

}

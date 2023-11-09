package uc.dei.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

public class OwnerPetCount {
    @Id
    private Long id;
    @Column("name")
    private String name;
    @Column("pet_count")
    private int pet_count;

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getPetCount(){
        return this.pet_count;
    }


}

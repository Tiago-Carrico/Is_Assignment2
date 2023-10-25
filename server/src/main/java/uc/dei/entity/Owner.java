package uc.dei.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("owner")
public class Owner {
    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("phone")
    private int phone;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPhone(){
        return phone;
    }

    public void setPhone(int phone){
        this.phone = phone;
    }
}

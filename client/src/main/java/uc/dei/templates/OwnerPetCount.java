package uc.dei.templates;

public class OwnerPetCount {
    private Long id;
    private String name;
    private int pet_count;

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

    public int getPetCount(){
        return this.pet_count;
    }

    public void setPetCount(int count){
        this.pet_count = count;
    }

    public String toString(){
        return (" Owner name: " + name + " number of pets: " + pet_count);
    }
}

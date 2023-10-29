package uc.dei.templates;

/**
 * Used for Data Transfer (DT) from the server to the client
 */
public class OwnerDTO {
    
    private String name;
    private int phone;

    public OwnerDTO(){}

    public OwnerDTO(Owner owner){
        this.name = owner.getName();
        this.phone = owner.getPhone();
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

	@Override
	public String toString(){
		return "Owner: name-> " + name + " phone number-> " + phone;
	}
}

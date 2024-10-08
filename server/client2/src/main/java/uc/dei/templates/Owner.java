package uc.dei.templates;

import java.util.ArrayList;

public class Owner {
    
    private Long id;
    private String name;
    private int phone;

    public Owner(Long id, String name, int phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
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

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

}

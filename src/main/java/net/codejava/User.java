package net.codejava;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="productuser")
public class User 
{
	@Id
private String name;
private String pasword;
private String age;
private String address;
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPasword() {
	return pasword;
}
public void setPasword(String pasword) {
	this.pasword = pasword;
}

}

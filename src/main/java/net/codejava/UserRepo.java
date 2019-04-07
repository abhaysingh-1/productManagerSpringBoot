package net.codejava;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
	
	User findByNameAndPasword(String name,String password);
	User findByName(String name);

}

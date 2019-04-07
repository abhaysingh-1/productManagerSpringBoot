package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	
	UserRepo userRepo;
	
	public List<User> listAll() {
		return userRepo.findAll();
	}
	
	public void save(User product) {
		userRepo.save(product);
	}
	
	public User get(String id) {
		return userRepo.findById(id).get();
	}
	
	public void delete(String id) {
		userRepo.deleteById(id);
	}
}

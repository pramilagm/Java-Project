package com.pramila.javaproject.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.pramila.javaproject.model.User;
import com.pramila.javaproject.repositories.UserRepository;


@Service
public class UserService {
	 private final UserRepository userRepository;
	    
	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
	    
	    
	    // register user and hash their password
	    public User registerUser(@Valid User user) {
	        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	        user.setPassword(hashed);
	        return userRepository.save(user);
	    }
	    
	    // find user by email
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	    
	    
	    // find user by id
	    public User findUserById(Long id) {
	    	Optional<User> u = userRepository.findById(id);
	    	
	    	if(u.isPresent()) {
	            return u.get();
	    	} else {
	    	    return null;
	    	}
	    }
	    
	    // authenticate user
	    public boolean authenticateUser(String email, String password) {
	        // first find the user by email
	        User user = userRepository.findByEmail(email);
	        // if we can't find it by email, return false
	        if(user == null) {
	            return false;
	        } else {
	            // if the passwords match, return true, else, return false
	            if(BCrypt.checkpw(password, user.getPassword())) {
	                return true;
	            } else {
	                return false;
	            }
	        }
	    }

		public List<User> findAllUser() {
			return (List<User>) userRepository.findAll();
		}

		
			public boolean checkUser(String email) {
		        User user = userRepository.findByEmail(email);
		        if(user == null) {
		            return false;
		        }
		        else {
		        	return true;
		        }
		    
		}
			
			
			
}
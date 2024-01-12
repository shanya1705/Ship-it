package com.gautami.shipit.service;



import com.gautami.shipit.dto.UserRequest;
import com.gautami.shipit.exceptions.AlreadyExists;
import com.gautami.shipit.model.Role;
import com.gautami.shipit.model.User;
import com.gautami.shipit.repository.RoleRepository;
import com.gautami.shipit.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserService(UserRepository userRepository,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository=roleRepository;
	}

	public void createUser(UserRequest userRequest) {

		User existingUser=userRepository.findByEmail(userRequest.getEmail());
		if(existingUser!=null){
			throw new AlreadyExists("A user with the given email Id Already exists: "+userRequest.getEmail());
		}
		
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());
				
		User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setPassword(encodedPassword);
		user.setEmail(userRequest.getEmail());
		Role role = roleRepository.findRoleByRoleName("ROLE_USER");
		if (role == null) {
			role = new Role();
			role.setRoleName("ROLE_USER");
			roleRepository.save(role);
		}

		Set<Role> roleSet = new HashSet<>();
		roleSet.add(role);
		user.setRoles(roleSet);

		userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	public void updateUser(Long id,UserRequest userRequest) {
		User existingUser=userRepository.findByEmail(userRequest.getEmail());
		if(existingUser!=null&&existingUser.getId()!=id){
			throw new AlreadyExists("A user with the given email Id Already exists: "+userRequest.getEmail());
		}
		User user=userRepository.findById(id).get();
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());

		user.setUsername(userRequest.getUsername());
		user.setPassword(encodedPassword);
		user.setEmail(userRequest.getEmail());
		userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public void createAdminUser() {
		if (!userRepository.findByUsername("ADMIN").isPresent()) {
			User adminUser = new User();
			adminUser.setUsername("admin");
			BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
			String encodedPassword = bCryptPasswordEncoder.encode("password");
			adminUser.setPassword(encodedPassword);
			adminUser.setEmail("guatami1705@gmail.com");
			Role role=new Role();
			role.setRoleName("ROLE_ADMIN");
			Set<Role> roleSet=new HashSet<>();
			roleSet.add(role);
			adminUser.setRoles(roleSet);
			userRepository.save(adminUser);
		}
	}
}

package io.hades.services;

import java.util.Locale;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.hades.constants.Constants;
import io.hades.dto.Role;
import io.hades.dto.User;
import io.hades.persistence.IUserRepository;
import io.hades.persistence.RoleRepositoryService;
import io.hades.services.interfaces.IUserServices;

@Service
public class UserServices implements IUserServices{

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//for test
	@Autowired
	private RoleRepositoryService roleRepositoryService;
	
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(()->new EntityNotFoundException(messageSource.getMessage(Constants.USER_ID_NOT_FOUND, new Object[] {id}, Locale.getDefault())));//Locale can be read from context
	}

	@Override
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User updateUserById(Long id, User user) {
		if(id == user.getId() && userRepository.existsById(id) ) {
			return userRepository.save(user);
		}
		else {
			throw new EntityNotFoundException(messageSource.getMessage(Constants.USER_ID_NOT_FOUND, new Object[] {id}, Locale.getDefault())); 
		}
	}

	@Override
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName)
				.orElseThrow(()->new EntityNotFoundException(messageSource.getMessage(Constants.USERNAME_NOT_FOUND, new Object[] {userName}, Locale.getDefault())));
	}

	//only for test
	@Override
	public Role getRoleById(Long roleId) {
		return roleRepositoryService.getRoleById(roleId);
	}

}

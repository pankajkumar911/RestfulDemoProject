package io.hades.services.interfaces;

import io.hades.dto.Role;
import io.hades.dto.User;

public interface IUserServices {

	User getUserById(Long id);

	User createUser(User user);

	void deleteUserById(Long id);

	User updateUserById(Long id, User user);

	User getUserByUserName(String userName);

	//only for test
	Role getRoleById(Long roleId);

}

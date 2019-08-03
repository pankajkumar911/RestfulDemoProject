package io.hades.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.hades.dto.User;

public interface IUserRepository extends CrudRepository<User, Long>{
	Optional<User> findByUserName(String userName);
}

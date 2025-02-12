package snt.repositories;

import org.springframework.data.repository.CrudRepository;
import snt.entities.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findUserByUsername(String userName);
 }
package org.halilkrkn.webapplication.repository;

import org.halilkrkn.webapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}

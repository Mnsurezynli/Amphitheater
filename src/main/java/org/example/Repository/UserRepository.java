package org.example.Repository;

import org.example.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User saveAndFlush(User user);

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameAndPassword(String userName, String password);
}

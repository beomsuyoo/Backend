package org.delivery.db.user;

import org.delivery.db.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    //select * from user where id = ? and status = ? order by id desc limit 1
    Optional<UserEntity>findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    // select * from user where email = ? and password = ? order by id desc limt 1
    Optional<UserEntity>findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);
}

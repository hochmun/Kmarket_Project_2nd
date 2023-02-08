package kr.co.kmarket.repository;

import kr.co.kmarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, String> {
}

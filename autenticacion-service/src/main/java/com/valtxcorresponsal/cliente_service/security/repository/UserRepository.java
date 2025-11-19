package com.valtxcorresponsal.cliente_service.security.repository;

import com.valtxcorresponsal.cliente_service.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserNameIgnoreCase(String username);
}

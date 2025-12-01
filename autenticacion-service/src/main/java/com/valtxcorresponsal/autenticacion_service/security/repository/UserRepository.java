package com.valtxcorresponsal.autenticacion_service.security.repository;

import com.valtxcorresponsal.autenticacion_service.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserNameIgnoreCase(String username);

    @Query("SELECT u.codAgente FROM UserEntity u WHERE LOWER(u.userName) = LOWER(:userName)")
    Long findCodAgenteByUserName(String userName);
}

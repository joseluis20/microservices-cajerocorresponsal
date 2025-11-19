package com.valtxcorresponsal.cliente_service.security.service.security;

import com.valtxcorresponsal.cliente_service.security.entity.UserEntity;
import com.valtxcorresponsal.cliente_service.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUserNameIgnoreCase(username);

		if (isNull(userEntity)) {
			throw new UsernameNotFoundException("El usuario " + username + " no existe");
		}

		UserDetails userDetails = User.builder()
				.username(userEntity.getUserName())
				.password(userEntity.getPassword())
                .roles(userEntity.getRol())
                .build();

		return userDetails;
	}

}

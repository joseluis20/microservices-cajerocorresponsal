package com.valtxcorresponsal.cliente_service.security.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

import static com.valtxcorresponsal.cliente_service.security.constants.SecurityConstants.EXPIRE;
import static com.valtxcorresponsal.cliente_service.security.constants.SecurityConstants.ISSUER_INFO;


@Service
public class JwtServiceImpl implements JwtService {


	@Value("${token.signing.key}")
	private String jwtSigningKey;

	@Override
	public String extractUser(String token) {
		return getAllClaims(token).getSubject();
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUser(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		List<String> roles = authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.toList();

		return Jwts.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.claim("roles", roles)
				.issuer(ISSUER_INFO)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRE))
				// .claim("idsistema", "1") //
				.signWith(getSigningSecretKey())
				.compact();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return getAllClaims(token).getExpiration();
	}

	public Claims getAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	private SecretKey getSigningSecretKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}

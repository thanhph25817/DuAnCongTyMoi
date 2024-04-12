package com.example.demo.util.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class for Spring Security.
 *
 * @author duongduc
 * @version 1.0
 * @since 2024-04-08
 */
public class SecurityUtils {

	/**
	 * Lấy thông tin quyền của user
	 */
	public static List<GrantedAuthority> getAuthorities(List<String> authorities) {
		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	/**
	 * Get the JWT of the current user.
	 *
	 * @return the JWT of the current user.
	 */
	public static Optional<String> getCurrentUserJWT() {
		SecurityContext securityContext = SecurityContextHolder.getContext();

		return Optional.ofNullable(securityContext.getAuthentication())
				.filter(authentication -> authentication.getCredentials() instanceof String)
				.map(authentication -> (String) authentication.getCredentials());
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user.
	 */
	public static Optional<String> getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();

		return Optional.ofNullable(securityContext.getAuthentication()).map(authentication -> {
			if (authentication instanceof AnonymousAuthenticationToken) {
				return null;
			}
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();

				return springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				return (String) authentication.getPrincipal();
			}

			return null;
		});
	}

	/**
	 * Lấy thông tin tên của authority
	 */
	public static List<String> getNameAuthorities(Collection<GrantedAuthority> authorities) {
		return authorities.stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
	}

	/**
	 * Check if a user is authenticated.
	 *
	 * @return true if the user is authenticated, false otherwise.
	 */
	public static boolean isAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.getContext();

		return Optional.ofNullable(securityContext.getAuthentication())
				.map(authentication -> !authentication.getAuthorities().isEmpty()).orElse(false);
	}
}

package com.springsecurityjwt.entity;

import java.util.Collection;

import org.hibernate.annotations.DialectOverride.GeneratedColumns;
import org.hibernate.annotations.GeneratedColumn;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users_JWT-1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long Id;
	private String firstname;
	private String secondname;
	private String email;
	private String password;
	private String phone;
	
	private Role role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getUsername() {
		return null;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	@Override
	public boolean isEnabled() {
		return false;
	}
}

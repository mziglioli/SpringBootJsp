package com.test.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Menu;
import com.test.model.UserAuthority;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	public Collection<Menu> findByAuthorities(Collection<UserAuthority> collection);

}
package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
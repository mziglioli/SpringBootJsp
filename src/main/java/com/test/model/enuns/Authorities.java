package com.test.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authorities {

	//@formatter:off
	USER("ROLE_USER")
	, MANAGER("ROLE_MANAGER")
	, ADMIN("ROLE_ADMIN");
	//@formatter:on

	private String role;
}

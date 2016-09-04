package com.test.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authorities {
	
	USER("ROLE_USER")
	,PROFISSIONAL("ROLE_PROFISSIONAL")
	,ADMIN("ROLE_ADMIN");
	
	private String role;
}

package com.test.model.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm implements Serializable {

	private static final long serialVersionUID = -5812463348093830776L;
	@NotNull
	private Long id;
	@NotNull(message = "error.empty.name")
	private String name;
	@NotNull(message = "error.empty.username")
	private String username;
	private String state;
	private boolean user;
	private boolean manager;
	private boolean admin;
}
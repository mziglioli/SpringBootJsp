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
	@NotNull
	private String name;
	@NotNull
	private String username;
	@NotNull
	private String status;
	@NotNull
	private boolean user;
	@NotNull
	private boolean manager;
	@NotNull
	private boolean admin;
}
package com.test.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import com.test.util.Catalago;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "id", "name", "link" })
@Entity
@Table(name = "menu", catalog = Catalago.DB_NAME)
public class Menu implements EntityJpaClass, Serializable {

	private static final long serialVersionUID = -1180260903735482908L;

	@Id
	@TableGenerator(name = "menu_generator", table = "GENERATED_KEYS", pkColumnName = "PK_COLUMN", valueColumnName = "VALUE_COLUMN", pkColumnValue = "id_menu", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "menu_generator")
	private Long id;

	@Column
	@NotNull
	private String name;

	@Column
	@NotNull
	private String icon;

	@Column
	@NotNull
	private String link;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> authorities;

}
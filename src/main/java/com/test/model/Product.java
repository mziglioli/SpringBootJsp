package com.test.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.test.util.Catalago;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(callSuper = false, of = { "id", "name" })
@Entity
@Table(name = "product", catalog = Catalago.DB_NAME, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }) })
public class Product implements EntityJpaClass, Serializable {

	private static final long serialVersionUID = -523519340365927865L;

	@Id
	@TableGenerator(name = "product_generator", table = "GENERATED_KEYS", pkColumnName = "PK_COLUMN", valueColumnName = "VALUE_COLUMN", pkColumnValue = "id_product", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "product_generator")
	private Long id;

	@Column
	@NotNull(message = "error.empty.name")
	private String name;

	@Column
	@NotNull(message = "error.empty.category")
	private String category;

	@Column
	@NotNull(message = "error.empty.price")
	private Double price;

}
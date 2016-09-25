package com.test.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@NotEmpty(message = "error.empty.name")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", nullable = false)
	@NotNull(message = "error.empty.category")
	@JsonManagedReference
	private Category category;

	@Column
	@NotNull(message = "error.empty.price")
	private Double price;

	public String getValueByPropertyName(String propertyName) {
		if (propertyName.equals("id")) {
			return id.toString();
		}
		if (propertyName.equals("name")) {
			return name;
		}
		if (propertyName.equals("category")) {
			return category.getName();
		}
		if (propertyName.equals("price")) {
			return price.toString();
		}
		return "";
	}
}
package com.test.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.model.enuns.Authorities;
import com.test.model.enuns.Status;
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
@Table(name = "user", catalog = Catalago.DB_NAME, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "username" }) })
public class User implements EntityJpaClass, Serializable, UserDetails {

	private static final long serialVersionUID = 442738873666572571L;

	@Id
	@TableGenerator(name = "user_generator", table = "GENERATED_KEYS", pkColumnName = "PK_COLUMN", valueColumnName = "VALUE_COLUMN", pkColumnValue = "id_user", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_generator")
	private Long id;

	@Column
	@NotNull(message = "error.empty.name")
	private String name;

	@Column
	@NotNull(message = "error.empty.username")
	private String username;

	@JsonIgnore
	@Column
	private String password;

	@Column
	private String status;

	@ElementCollection(fetch = FetchType.EAGER)
	private Collection<UserAuthority> authorities;

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		if (Status.ATIVO.getDescricao().equals(status)) {
			return true;
		}
		return false;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Collection<UserAuthority> getUserAuth() {
		return authorities;
	}

	public Set<String> getAuth() {
		try (Stream<String> auths = getUserAuth().stream().map(UserAuthority::getAuthority)) {
			return auths.collect(Collectors.toSet());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isUser() {
		try (Stream<String> auths = getUserAuth().stream().map(UserAuthority::getAuthority)) {
			return auths.filter(a -> a.equals(Authorities.USER.getRole())).findAny().isPresent();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isManager() {
		try (Stream<String> auths = getUserAuth().stream().map(UserAuthority::getAuthority)) {
			return auths.filter(a -> a.equals(Authorities.MANAGER.getRole())).findAny().isPresent();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isAdmin() {
		try (Stream<String> auths = getUserAuth().stream().map(UserAuthority::getAuthority)) {
			return auths.filter(a -> a.equals(Authorities.ADMIN.getRole())).findAny().isPresent();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * used on table.jsp way to table be generic
	 */
	public String getValueByPropertyName(String propertyName) {
		if (propertyName.equals("id")) {
			return id.toString();
		}
		if (propertyName.equals("name")) {
			return name;
		}
		if (propertyName.equals("username")) {
			return username;
		}
		if (propertyName.equals("status")) {
			return status;
		}
		if (propertyName.equals("authorities")) {
			return getAuth().toString();
		}
		return "";
	}
}
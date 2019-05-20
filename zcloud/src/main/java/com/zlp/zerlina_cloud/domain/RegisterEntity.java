package com.zlp.zerlina_cloud.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "register", schema = "zcloud")
public class RegisterEntity {
	private String uid;
	private String username;
	private String email;
	private String password;

	@Id
	@Column(name = "uid", nullable = false, length = 40)
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Basic
	@Column(name = "username", nullable = true, length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@Column(name = "email", nullable = true, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "password", nullable = true, length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RegisterEntity that = (RegisterEntity) o;
		return Objects.equals(uid, that.uid) &&
				Objects.equals(username, that.username) &&
				Objects.equals(email, that.email) &&
				Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {

		return Objects.hash(uid, username, email, password);
	}
}

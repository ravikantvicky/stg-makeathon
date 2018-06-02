package com.stg.makeathon.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserPrefId implements Serializable{
	private static final long serialVersionUID = 2222322462833919171L;
	@Column(name = "userid")
	private int userId;
	@Column(name = "pref_id")
	private int prefId;

	public UserPrefId() {
		super();
	}

	public UserPrefId(int userId, int prefId) {
		super();
		this.userId = userId;
		this.prefId = prefId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPrefId() {
		return prefId;
	}

	public void setPrefId(int prefId) {
		this.prefId = prefId;
	}
}

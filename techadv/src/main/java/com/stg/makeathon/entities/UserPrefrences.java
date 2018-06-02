package com.stg.makeathon.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_prefrences")
public class UserPrefrences {
	@EmbeddedId
	private UserPrefId id;
	@Column(name = "click_count")
	private int clickCount;

	public UserPrefrences() {
		super();
	}

	public UserPrefrences(UserPrefId id, int clickCount) {
		super();
		this.id = id;
		this.clickCount = clickCount;
	}

	public UserPrefId getId() {
		return id;
	}

	public void setId(UserPrefId id) {
		this.id = id;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
}

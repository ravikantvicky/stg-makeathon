package com.stg.makeathon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pref_topics")
public class PrefTopics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pref_id")
	private int prefId;
	@Column(name = "pref_topic")
	private String prefTopic;

	public int getPrefId() {
		return prefId;
	}

	public void setPrefId(int prefId) {
		this.prefId = prefId;
	}

	public String getPrefTopic() {
		return prefTopic;
	}

	public void setPrefTopic(String prefTopic) {
		this.prefTopic = prefTopic;
	}
}

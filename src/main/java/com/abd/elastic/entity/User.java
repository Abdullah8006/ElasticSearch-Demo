package com.abd.elastic.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

/**
 *
 * @author : aaabdullah
 * @date : Apr 2, 2019
 */
@Document(indexName = "my_index", type = "user")
public class User {

	@Id
	private String userId;
	@MultiField(mainField = @Field(type = FieldType.Text, fielddata = true), otherFields = {
			@InnerField(suffix = "verbatim", type = FieldType.Keyword) })
	private String name;
	private String profession;
	private Date creationDate = new Date();
	private Map<String, String> userSettings = new HashMap<>();

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Map<String, String> getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(Map<String, String> userSettings) {
		this.userSettings = userSettings;
	}

}

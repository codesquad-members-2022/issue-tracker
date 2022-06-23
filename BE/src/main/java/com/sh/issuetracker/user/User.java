package com.sh.issuetracker.user;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Where(clause = "is_deleted = false")
@Table(name = "issue_tracker_user")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String nickName;
	private String email;
	private String password;
	private String image;
	private boolean isDeleted;

	public String nickName() {
		return nickName;
	}

	public String image() {
		return image;
	}
}

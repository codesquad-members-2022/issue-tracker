package com.sh.issuetracker.member;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"userId", "projectId"})
@Embeddable
public class MemberId implements Serializable {
	private Long userId;
	private Long projectId;
}

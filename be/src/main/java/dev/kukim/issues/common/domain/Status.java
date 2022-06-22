package dev.kukim.issues.common.domain;

import dev.kukim.issues.common.exception.InvalidSearchRequestParamException;

public enum Status {
	OPEN(true),
	CLOSE(false);

	private final boolean statusBoolType;

	Status(boolean statusBoolType) {
		this.statusBoolType = statusBoolType;
	}

	public static boolean statusToBoolean(String status) {
		try {
			return Status.valueOf(status.toUpperCase()).statusBoolType;
		} catch (RuntimeException e) {
			throw new InvalidSearchRequestParamException("유효하지 않은 status 입니다.");
		}
	}

}

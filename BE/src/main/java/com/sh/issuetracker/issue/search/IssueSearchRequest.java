package com.sh.issuetracker.issue.search;

import org.apache.logging.log4j.util.Strings;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IssueSearchRequest {
	private String q;

	public String getText() {
		if (Strings.isBlank(q)) {
			return "is:open";
		}
		return q;
	}
}

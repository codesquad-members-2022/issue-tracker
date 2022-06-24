package com.sh.issuetracker.issue.dto;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IssueSearchRequestTest {
	public static final String REGEX_MIDDLE_KEY_SEPARATOR = "[+](.*?)[:]";
	public static final String REGEX_MIDDLE_VALUE_SEPARATOR = "[:](.*?)[+]";
	/*
		todo + 영어대소문자숫자 :
		 시작 ^, 끝 \z
		 시작 +, 끝:
		 - 가운데 콜론이 있는 문자열 + 기준 split?
	 */

	@Test
	void parsing() {

		String url = "is:open+author:sally+label:\"BE\"+label:\"FE\"";
		// String url = Strings.toRootLowerCase(requestUrl);  // 검색시 대소문자 구분
		System.out.println(url);

		// 맨 앞 is: ~  -> 첫번째 + 기준으로 분리
		int firstPlusIdx = url.indexOf('+');
		System.out.println(firstPlusIdx);

		// TODO 앞,뒤
		String front = url.substring(0, firstPlusIdx);
		System.out.println("front = " + front);

		String behindSearchWords = url.substring(firstPlusIdx);
		System.out.println(behindSearchWords);

		Pattern patternOfKey = Pattern.compile(REGEX_MIDDLE_KEY_SEPARATOR);
		Pattern patternOfValue = Pattern.compile(REGEX_MIDDLE_VALUE_SEPARATOR);

		Matcher matcherOfKey = patternOfKey.matcher(behindSearchWords);
		Matcher matcherOfValue = patternOfValue.matcher(behindSearchWords);

		while (matcherOfKey.find() && matcherOfValue.find()) {
			System.out.println(matcherOfKey.group(1) + " : " + matcherOfValue.group(1));

			if (matcherOfKey.group(1) == null || matcherOfValue.group(1) == null)
				break;
		}

		String lastKey = matcherOfKey.group(1);
		int lastColonIdx = url.lastIndexOf(':');
		String lastValue = url.substring(lastColonIdx);
		System.out.println(lastKey + " : " + lastValue);
	}
}

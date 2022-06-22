package com.sh.issuetracker.label;

import java.util.Arrays;

public enum TextColor {
	BRIGHT, DARK;

	public static TextColor from(String colorName) {
		return Arrays.stream(values())
			.map(textColor -> textColor.valueOf(colorName))
			.findFirst()
			.orElseThrow(() -> new RuntimeException("no matched TextColor"));
	}
}

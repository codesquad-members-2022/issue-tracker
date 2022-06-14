package com.codesquad.issuetracker.domain;

import com.codesquad.issuetracker.excption.InvalidColorCodeException;
import com.codesquad.issuetracker.excption.InvalidColorValueRangeException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class LabelColor {

    public static final int COLOR_RADIX = 16;
    private static final int MIN_COLOR_VALUE = Integer.parseInt("000000", 16);
    private static final int MAX_COLOR_VALUE = Integer.parseInt("ffffff", 16);
    public static final String COLOR_CODE_FORMAT = "%06x";

    private int colorValue;

    private LabelColor(int colorValue) {
        this.colorValue = colorValue;
    }

    public static LabelColor create(String colorCode) {
        int colorValue = parseToColorValue(colorCode);
        validateColorValueRange(colorValue);
        return new LabelColor(colorValue);
    }

    private static int parseToColorValue(String colorCode) {
        try {
            int colorValue = Integer.parseInt(colorCode, COLOR_RADIX);
            return colorValue;
        } catch (NumberFormatException nfe) {
            throw new InvalidColorCodeException("형식에 맞지 않는 색상코드입니다.");
        }
    }

    private static void validateColorValueRange(int colorValue) {
        if (!isValidColorValueRange(colorValue)) {
            throw new InvalidColorValueRangeException("색상코드 16진수값이 유효하지 않습니다.");
        }
    }

    private static boolean isValidColorValueRange(int colorValue) {
        return MIN_COLOR_VALUE <= colorValue && colorValue <= MAX_COLOR_VALUE;
    }

    public String getColorCode() {
        return String.format(COLOR_CODE_FORMAT, colorValue);
    }

}

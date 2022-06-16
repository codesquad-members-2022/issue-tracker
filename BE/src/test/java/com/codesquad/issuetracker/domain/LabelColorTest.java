package com.codesquad.issuetracker.domain;

import com.codesquad.issuetracker.excption.InvalidColorCodeException;
import com.codesquad.issuetracker.excption.InvalidColorValueRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LabelColor 클래스의")
class LabelColorTest {

    @Nested
    @DisplayName("create 메서드는")
    class Create_메서드는 {
        @Nested
        @DisplayName("만약 색상코드의 최솟값인 '000000'이 주어지면")
        class 만약_색상코드의_최솟값인_000000_이_주어지면 {

            private String colorCode = "000000";

            @Test
            @DisplayName("색상값 정수 0을 가진 LabelColor를 생성하여 반환한다.")
            void 색상값_정수_0을_가진_LabelColor를_생성하여_반환한다() {
                LabelColor labelColor = LabelColor.create(colorCode);
                int colorValue = labelColor.getColorValue();
                assertThat(colorValue).isEqualTo(0);
            }
        }

        @Nested
        @DisplayName("만약 색상코드의 최댓값인 'ffffff'이 주어지면")
        class 만약_색상코드의_최댓값인_ffffff_이_주어지면 {

            private String colorCode = "ffffff";

            @Test
            @DisplayName("색상값 16777215를 가진 LabelColor를 생성하여 반환한다.")
            void 색상값_정수_16777215를_가진_LabelColor를_생성하여_반환한다() {
                LabelColor labelColor = LabelColor.create(colorCode);
                int colorValue = labelColor.getColorValue();
                assertThat(colorValue).isEqualTo(16777215);
            }
        }

        @Nested
        @DisplayName("만약 색상코드 형식에 맞지않는 'ㅁㄴㅇㄹ'가 주어지면")
        class 만약_잘못된_형식의_색상코드가_주어지면 {

            private String colorCode = "ㅁㄴㅇㄹ";

            @Test
            @DisplayName("InvalidColorCodeException이 발생한다.")
            void InvalidColorCodeException이_발생한다() {
                assertThatThrownBy(() -> LabelColor.create(colorCode))
                        .isInstanceOf(InvalidColorCodeException.class);
            }
        }

        @Nested
        @DisplayName("만약 색상코드 최댓값보다 큰 '1000000'이 주어지면")
        class 만약_색상코드_최댓값보다_큰_값이_주어지면 {

            private String colorCode = "1000000";

            @Test
            @DisplayName("InvalidColorValueRangeException이 발생한다.")
            void InvalidColorValueRangeException이_발생한다() {
                assertThatThrownBy(() -> LabelColor.create(colorCode))
                        .isInstanceOf(InvalidColorValueRangeException.class);
            }
        }

        @Nested
        @DisplayName("만약 색상코드 최솟값보다 작은 '-1'이 주어지면")
        class 만약_색상코드값이_최솟값보다_작은_값이_주어지면 {

            private String colorCode = "-1";

            @Test
            @DisplayName("InvalidColorValueRangeException이 발생한다.")
            void InvalidColorValueRangeException이_발생한다() {
                assertThatThrownBy(() -> LabelColor.create(colorCode))
                        .isInstanceOf(InvalidColorValueRangeException.class);
            }
        }
    }
}
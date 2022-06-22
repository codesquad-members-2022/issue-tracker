package kr.codesquad.issuetraker.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GeneralResponseDto {
    private int statusCode;
    private String message;
}

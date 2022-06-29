package kr.codesquad.issuetracker.domain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToStatus implements Converter<String, Status> {

	@Override
	public Status convert(String source) {
		return Status.valueOf(source.toUpperCase());
	}
}

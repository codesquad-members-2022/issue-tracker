package louie.hanse.issuetracker.converter;

import louie.hanse.issuetracker.domain.Status;
import org.springframework.core.convert.converter.Converter;

public class StringToStatusConverter implements Converter<String, Status> {
    @Override
    public Status convert(String source) {
        return Status.valueOfWithCaseInsensitive(source);
    }
}

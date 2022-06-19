package kr.codesquad.issuetracker.domain;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseTimeEntity {

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@CreatedDate
	private LocalDateTime createDateTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@LastModifiedDate
	private LocalDateTime modifiedDateTime	;
}

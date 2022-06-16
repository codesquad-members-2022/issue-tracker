package kr.codesquad.issuetracker.domain.comment;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import kr.codesquad.issuetracker.domain.BaseTimeEntity;
import kr.codesquad.issuetracker.domain.image.CommentImage;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.member.Member;

@Entity
@Table(name = "comments")
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comments_id")
	private Long id;

	private String content;

	@OneToMany(mappedBy = "comment_image_id")
	private List<CommentImage> commentImages = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	private Issue issue;
}

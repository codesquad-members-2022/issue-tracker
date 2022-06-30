package team29.hoorry.issuetracker.core.issue.domain;

import java.util.NoSuchElementException;

public enum Reaction {
	THUMBS_UP_SIGN("\uD83D\uDC4D"),
	THUMBS_DOWN_SIGN("\uD83D\uDC4E"),
	HEAVY_BLACK_HEART("❤️"),
	PARTY_POPPER("\uD83C\uDF89"),
	SMILE("\uD83D\uDE04"),
	CONFUSED_FACE("😕"),
	EYES("👀"),
	ROCKET("\uD83D\uDE80");

	private String emoji;

	Reaction(String emoji) {
		this.emoji = emoji;
	}

	public static Reaction findByEmoji(String emoji) {
		for (Reaction reaction : Reaction.values()) {
			if (emoji.equals(reaction.emoji)) {
				return reaction;
			}
		}
		throw new NoSuchElementException("해당 emoji는 존재하지 않습니다.");
	}

	public String getEmoji() {
		return emoji;
	}
}

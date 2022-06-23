import styled, { css } from "styled-components";

export const StyledPopup = styled.div<{ isLeft: boolean }>`
	${({ theme: { colors, fonts }, isLeft }) => css`
		width: 300px;
		display: block;
		position: absolute;
		margin-top: 10px;
		margin-left: -1px;
		border: solid 1px ${colors.line};
		border-radius: 20px;
		box-shadow: 0px 4px 8px -4px ${colors.body};
		overflow: hidden;

		${!isLeft &&
		css`
			right: 0;
		`}

		svg {
			margin-top: -2px;
		}

		> :first-child {
			${fonts.textMedium};
			background-color: ${colors.background};
			color: ${colors.titleActive};
		}

		> div:not(:first-child) {
			${fonts.textSmall};
			background-color: ${colors.offWhite};
		}

		> div:not(:last-child) {
			border-bottom: solid 1px ${colors.line};
		}

		> div {
			padding: 8px 16px;
		}
	`}
`;

export const StyledPopupWrapper = styled.div`
	z-index: 2;
	position: relative;
`;

type TStyledContentProps = {
	checked: boolean;
};

export const StyledContent = styled.div<TStyledContentProps>`
	${({ checked, theme: { colors } }) => css`
		color: ${colors.label};

		${checked &&
		css`
			font-weight: 700;
			color: ${colors.titleActive};
		`}

		display: flex;
		justify-content: space-between;
		align-items: center;
	`}
`;

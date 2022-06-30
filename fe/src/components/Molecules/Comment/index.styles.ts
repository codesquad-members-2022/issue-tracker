import styled from 'styled-components';
import { colors, fontStyles } from 'styles/theme';
import { CommentTypes } from 'components/Molecules/Comment';
import markdownReset from 'styles/markdown';

type StyledCommentTypes = Pick<CommentTypes, 'isOpen' | 'isNewComment'> & { isEditable: boolean };

export const StyledComment = styled.div<StyledCommentTypes>`
  display: table;
  border-collapse: collapse;
  border-style: hidden;
  box-shadow: 0 0 0 1px ${({ isOpen }) => (isOpen ? colors.line : colors.secondary.purple)};
  ${({ isEditable, isNewComment }) => (isEditable || isNewComment) && `box-shadow:none;`}
  border-radius: 16px;
  width: 880px;
  height: 124px;
  overflow: hidden;
  margin-left: 16px;

  & .table-row {
    display: table-row;
    border-bottom: 1px solid ${({ isOpen }) => (isOpen ? colors.line : colors.secondary.purple)};
  }

  span {
    ${fontStyles.textSmall};
  }

  form {
    width: inherit;
  }
`;

export const CommentHeader = styled.div<{ isOpen: boolean }>`
  display: flex;
  justify-content: space-between;
  padding: 24px 18px;
  background: ${({ isOpen }) => (isOpen ? colors.background : colors.secondary.lightPurple)};

  div {
    display: flex;
    align-items: center;
  }

  span + span {
    margin-left: 8px;
  }

  svg {
    margin-left: 20px;
  }

  .timeStamp,
  .editLabel {
    color: ${colors.label};
  }

  .editLabel {
    ${fontStyles.linkXsmall}
  }

  .commentBadge {
    span {
      margin: 2px 0px 0px 5px;
    }

    button {
      &:hover {
        cursor: pointer;
        color: ${({ theme }) => theme.colors.primary.blue};

        svg {
          stroke: ${({ theme }) => theme.colors.primary.blue};
        }
      }
    }
  }
`;

export const CommentContent = styled.div`
  padding: 24px 16px;
  background: ${colors.offWhite};

  ${markdownReset};
`;

export const EditButton = styled.div`
  &:hover {
    cursor: pointer;
  }
`;

export const EditButtons = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  button + button {
    margin-left: 8px;
  }
`;

export const StyledCommentList = styled.div`
  display: flex;
`;

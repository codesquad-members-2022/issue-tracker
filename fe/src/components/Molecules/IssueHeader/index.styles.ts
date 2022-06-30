import styled, { css } from 'styled-components';
import { colors, fontStyles } from 'styles/theme';

export const StyledIssueTitle = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 48px;
  .issueTitle,
  .issueButtons {
    display: flex;
  }
  .issueTitle {
    width: 100%;
    form {
      width: inherit;
      margin-right: 92px;
    }
  }
  h1 {
    font-weight: 400;
    font-size: 32px;
    line-height: 48px;
    color: ${colors.titleActive};
  }
  h2 {
    font-weight: 400;
    font-size: 32px;
    line-height: 48px;
    color: ${colors.label};
    margin-left: 16px;
  }

  .issueButtons {
    button + button {
      margin-left: 8px;
    }
  }
`;

export const StyledIssueInfo = styled.div<{ status: 'OPEN' | 'CLOSED' }>`
  display: flex;
  align-items: center;
  margin-top: 16px;
  color: ${colors.body};
  ${fontStyles.textMedium}

  div {
    padding: 10px 16px;
    & > span {
      ${fontStyles.textXSmall}
    }

    border: ${({ status }) =>
      status === 'OPEN'
        ? css`2px solid ${({ theme }) => theme.colors.primary.blue};`
        : css`2px solid ${({ theme }) => theme.colors.secondary.purple};`};
  }

  span {
    margin-left: 8px;
  }
`;

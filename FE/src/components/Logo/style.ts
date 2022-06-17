import styled, { css } from 'styled-components';

interface ILogoProps {
  type: 'large' | 'medium';
  title?: string;
}

const LargeStyle = css`
  font: ${({ theme }) => theme.TYPOGRAPHY.logo.large};
`;

const MediumStyle = css`
  font: ${({ theme }) => theme.TYPOGRAPHY.logo.medium};
`;

const Styled_logo = styled.h1<ILogoProps>`
  ${({ type }) => (type === 'large' ? LargeStyle : MediumStyle)}
  font-style: italic;
`;

export { Styled_logo };
export type { ILogoProps };

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

const $Logo = styled.h1<ILogoProps>`
  ${({ type }) => (type === 'large' ? LargeStyle : MediumStyle)}
  font-style: italic;
`;

export { $Logo };
export type { ILogoProps };

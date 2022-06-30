import styled from 'styled-components';

import { theme } from '@/styles/theme';

export type TextStyledProps = {
  color?: keyof typeof theme.color;
  fontSize?: keyof typeof theme.fontSize;
  fontWeight?: keyof typeof theme.fontWeight;
  styles?: string;
};

export const Text = styled.span.attrs<TextStyledProps>(
  ({ color = 'titleArchieve', fontSize = 'base', fontWeight = 'base' }) => ({
    style: {
      color: theme.color[color],
      fontSize: theme.fontSize[fontSize],
      fontWeight: theme.fontWeight[fontWeight],
    },
  }),
)<TextStyledProps>`
  display: flex;
  align-items: center;
  ${({ styles }) => styles}
`;

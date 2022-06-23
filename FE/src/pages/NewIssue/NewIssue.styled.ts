import styled from 'styled-components';

import { TextInputStyle } from 'common/textInput.styled';

export const AAA = styled.input<{
  size: string | any;
  success?: boolean;
  error?: boolean;
}>`
  ${TextInputStyle}
`;

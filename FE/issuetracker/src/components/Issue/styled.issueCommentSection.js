import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const commentSection = styled.section`
  ${mixin.flexbox({ dir: 'column' })};
  width: 940px;
  height: 124px;
  margin-right: 16px;
`;

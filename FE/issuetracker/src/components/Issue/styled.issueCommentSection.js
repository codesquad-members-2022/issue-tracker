import styled from 'styled-components';
<<<<<<< HEAD

export const commentSection = styled.section`
  width: 940px;
  margin: 16px 16px 0 0;
=======
import { mixin } from 'design/GlobalStyles';

export const commentSection = styled.section`
  ${mixin.flexbox({ dir: 'column' })};
  width: 940px;
  height: 124px;
  margin-right: 16px;
>>>>>>> origin/11-feat-fe-newissue-레이아웃-구현
`;

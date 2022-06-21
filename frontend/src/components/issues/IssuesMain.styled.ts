import { flexLayoutMixin } from 'src/utils/utils';
import styled from 'styled-components';

export const IssuesMainBox = styled.div`
  ${flexLayoutMixin('column', 'center', 'center')};
  width: 63%;
  border-radius: 6px;
  border: 1px solid #444c56;
`;

export const IssuesItems = styled.div`
  width: 100%;
`;

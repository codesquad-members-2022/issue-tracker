import { flexLayoutMixin } from 'src/utils/utils';
import styled from 'styled-components';

const AttributeBox = styled.div`
  ${flexLayoutMixin('row', 'center', 'center')}
  padding: 5px 16px;
  gap: 3px;
  border: 2px solid #373e47;
  border-radius: 6px;
  &:hover {
    background-color: #2d333b;
    cursor: pointer;
  }
`;

export default AttributeBox;

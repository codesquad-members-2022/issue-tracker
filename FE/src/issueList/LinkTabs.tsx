import styled from 'styled-components';
import { GREYSCALE, PATH } from '@constants';
import { Link } from 'react-router-dom';
import Tab from '@components/Tab';

function LinkTabs() {
  return (
    <TabsBox>
      <Link to={`/${PATH.LABEL_LIST}`}>
        <Tab isActive={false} iconName="tag" title="레이블" count={0} />
      </Link>
      <span />
      <Link to={`/${PATH.MILESTONE_LIST}`}>
        <Tab isActive={false} iconName="milestone" title="마일스톤" count={0} />
      </Link>
    </TabsBox>
  );
}

const TabsBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row')}
  border-radius: 12px;
  border: 1px solid ${GREYSCALE.LINE};
  overflow: hidden;

  span {
    width: 1px;
    background-color: ${GREYSCALE.LINE};
  }
`;

export default LinkTabs;

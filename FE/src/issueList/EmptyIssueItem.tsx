import { GREYSCALE } from '@/constants';
import styled from 'styled-components';

function EmptyIssueItem() {
  return <EmptyIssueItemBox>등록된 이슈가 없습니다.</EmptyIssueItemBox>;
}

const EmptyIssueItemBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'center', 'center')}
  width: 1280px;
  height: 100px;
  border: 1px solid ${GREYSCALE.LINE};
  border-top: none;
  border-radius: 0px 0px 16px 16px;
  color: ${GREYSCALE.LABEL};
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_SMALL}
`;

export default EmptyIssueItem;

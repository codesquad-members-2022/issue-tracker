import styled from 'styled-components';
import { GREYSCALE } from '@/constants';
import { IssueHeaderBox, IssueTabs } from './IssueHeader';
import IssueTab from './IssueTab';
import CheckBox from './CheckBox';
import { useIssueListContext } from './IssueListProvider';

function ClickedIssueHeader() {
  const { state, dispatch } = useIssueListContext();

  const selectedIssuesCount = Object.values(state.selectedIssues).filter(
    (selected) => selected
  ).length;

  const handleIssueHeaderCheckBoxClick = () => {
    if (state.headerCheckBox === 'active') {
      dispatch({ type: 'ALL_UNCHECK', payload: {} });
    } else {
      dispatch({ type: 'ALL_CHECK', payload: {} });
    }
  };

  return (
    <IssueHeaderBox>
      <CheckBox
        checkBoxType={state.headerCheckBox}
        onClick={handleIssueHeaderCheckBoxClick}
      />
      <SelectedIssuesCountBox>
        {selectedIssuesCount}개 이슈 선택
      </SelectedIssuesCountBox>
      <IssueTabs>
        <IssueTab tabName="상태 수정" />
      </IssueTabs>
    </IssueHeaderBox>
  );
}

const SelectedIssuesCountBox = styled.p`
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
  color: ${GREYSCALE.LABEL};
`;

export default ClickedIssueHeader;

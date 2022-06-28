import { GREYSCALE } from '@/constants';
import { Dispatch, SetStateAction } from 'react';
import styled from 'styled-components';
import CheckBox from './CheckBox';
import { IssueListStateType } from './IssueList';
import { useIssueListContext } from './IssueListProvider';
import IssueMenu from './IssueMenu';
import IssueTab from './IssueTab';

type IssueHeaderProps = {
  issueListState: IssueListStateType;
  setIssueListState: Dispatch<SetStateAction<IssueListStateType>>;
  openedIssueCount: number;
  closedIssueCount: number;
};

function IssueHeader({
  issueListState,
  setIssueListState,
  openedIssueCount,
  closedIssueCount
}: IssueHeaderProps) {
  const { state, dispatch } = useIssueListContext();

  const handleHeaderCheckBoxClick = () => {
    dispatch({ type: 'ALL_CHECK', payload: {} });
  };

  return (
    <IssueHeaderBox>
      <CheckBox
        checkBoxType={state.headerCheckBox}
        onClick={handleHeaderCheckBoxClick}
      />
      <IssueMenus>
        <IssueMenu
          icon="alertCircle"
          menuName="열린 이슈"
          count={openedIssueCount}
          isCurrent={issueListState === 'opened'}
          onClick={() => setIssueListState('opened')}
        />
        <IssueMenu
          icon="archive"
          menuName="닫힌 이슈"
          count={closedIssueCount}
          isCurrent={issueListState === 'closed'}
          onClick={() => setIssueListState('closed')}
        />
      </IssueMenus>
      <IssueTabs>
        <IssueTab tabName="담당자" />
        <IssueTab tabName="레이블" />
        <IssueTab tabName="마일스톤" />
        <IssueTab tabName="작성자" />
      </IssueTabs>
    </IssueHeaderBox>
  );
}

export const IssueHeaderBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start', 'center')}
  width: 1280px;
  height: 64px;
  background-color: ${GREYSCALE.BACKGROUND};
  border: 1px solid ${GREYSCALE.LINE};
  border-radius: 16px 16px 0px 0px;
  padding: 0 32px;
  gap: 32px;
`;

const IssueMenus = styled.ul`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin()}
  gap: 24px;
`;

export const IssueTabs = styled.ul`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin()}
  margin-left: auto;
  gap: 32px;
`;

export default IssueHeader;

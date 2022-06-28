import { GREYSCALE } from '@/constants';
import { Dispatch, SetStateAction } from 'react';
import styled from 'styled-components';
import CheckBox, { CheckBoxType } from './CheckBox';
import { IssueListStateType, SelectedIssueType } from './IssueList';
import IssueMenu from './IssueMenu';
import IssueTab from './IssueTab';

type IssueHeaderProps = {
  issueListState: IssueListStateType;
  setIssueListState: Dispatch<SetStateAction<IssueListStateType>>;
  openedIssueCount: number;
  closedIssueCount: number;
  selectedIssues: SelectedIssueType;
  setSelectedIssues: Dispatch<SetStateAction<SelectedIssueType>>;
  headerCheckBoxType: CheckBoxType;
  setHeaderCheckBoxType: Dispatch<SetStateAction<CheckBoxType>>;
};

function IssueHeader({
  issueListState,
  setIssueListState,
  openedIssueCount,
  closedIssueCount,
  selectedIssues,
  setSelectedIssues,
  headerCheckBoxType,
  setHeaderCheckBoxType
}: IssueHeaderProps) {
  const selectedIssuesCount = Object.values(selectedIssues).filter(
    (selected) => selected
  ).length;

  const setAllIssuesState = (state: boolean) => {
    const updatedSelectedIssues = { ...selectedIssues };
    Object.keys(updatedSelectedIssues).forEach((id) => {
      updatedSelectedIssues[id] = state;
    });
    return setSelectedIssues({ ...updatedSelectedIssues });
  };

  const handleClick = () => {
    if (headerCheckBoxType === 'initial' || headerCheckBoxType === 'disable') {
      setHeaderCheckBoxType('active');
      setAllIssuesState(true);
    } else {
      setHeaderCheckBoxType('initial');
      setAllIssuesState(false);
    }
  };

  return (
    <IssueHeaderBox>
      <CheckBox checkBoxType={headerCheckBoxType} onClick={handleClick} />
      {!!selectedIssuesCount ? (
        <>
          <SelectedIssuesCountBox>
            {selectedIssuesCount}개 이슈 선택
          </SelectedIssuesCountBox>
          <IssueTabs>
            <IssueTab tabName="상태 수정" />
          </IssueTabs>
        </>
      ) : (
        <>
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
        </>
      )}
    </IssueHeaderBox>
  );
}

const IssueHeaderBox = styled.div`
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

const SelectedIssuesCountBox = styled.p`
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
  color: ${GREYSCALE.LABEL};
`;

const IssueTabs = styled.ul`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin()}
  margin-left: auto;
  gap: 32px;
`;

export default IssueHeader;

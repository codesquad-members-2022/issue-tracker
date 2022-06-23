import { GREYSCALE } from '@/constants';
import { Dispatch, SetStateAction } from 'react';
import styled from 'styled-components';
import CheckBox, { CheckBoxType } from './CheckBox';
import { SelectedIssueType } from './IssueList';
import IssueMenu from './IssueMenu';
import IssueTab from './IssueTab';

type IssueHeaderProps = {
  selectedIssues: SelectedIssueType;
  setSelectedIssues: Dispatch<SetStateAction<SelectedIssueType>>;
  headerCheckBoxType: CheckBoxType;
  setHeaderCheckBoxType: Dispatch<SetStateAction<CheckBoxType>>;
};

function IssueHeader({
  selectedIssues,
  setSelectedIssues,
  headerCheckBoxType,
  setHeaderCheckBoxType
}: IssueHeaderProps) {
  const selectedIssuesCount = Object.values(selectedIssues).filter(
    (selected) => selected
  ).length;

  const setAllIssuesState = (state: boolean) => {
    Object.keys(selectedIssues).forEach((id) => {
      selectedIssues[id] = state;
    });
    setSelectedIssues({ ...selectedIssues });
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
      {selectedIssuesCount ? (
        <>
          <SelectedIssuesCount>
            {selectedIssuesCount}개 이슈 선택
          </SelectedIssuesCount>
          <IssueTabs>
            <IssueTab tabName="상태 수정" />
          </IssueTabs>
        </>
      ) : (
        <>
          <IssueMenus>
            <IssueMenu
              icon={'alertCircle'}
              menuName={'열린 이슈'}
              count={2}
              isCurrent
            />
            <IssueMenu
              icon={'archive'}
              menuName={'닫힌 이슈'}
              count={0}
              isCurrent={false}
            />
          </IssueMenus>
          <IssueTabs>
            <IssueTab tabName={'담당자'} />
            <IssueTab tabName={'레이블'} />
            <IssueTab tabName={'마일스톤'} />
            <IssueTab tabName={'작성자'} />
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

const SelectedIssuesCount = styled.p`
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
  color: ${GREYSCALE.LABEL};
`;

const IssueTabs = styled.ul`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin()}
  margin-left: auto;
  gap: 32px;
`;

export default IssueHeader;

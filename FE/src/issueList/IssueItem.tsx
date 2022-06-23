import Icon from '@/assets/icons/Icon';
import { COLORS, GREYSCALE } from '@/constants';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import CheckBox from './CheckBox';
import Label from './Label';

type IssueItemProps = {
  id: string;
  isSelected: boolean;
  isLast: boolean;
  updateIssueState: (id: string) => void;
};

function IssueItem({
  id,
  isSelected,
  isLast,
  updateIssueState
}: IssueItemProps) {
  const IssueItemBox = isLast ? LastIssueItemBox : DefaultIssueItemBox;
  const checkBoxType = isSelected ? 'active' : 'initial';

  const handleClick = () => {
    updateIssueState(id);
  };

  return (
    <IssueItemBox>
      <CheckBox checkBoxType={checkBoxType} onClick={handleClick} />
      <IssueText>
        <IssueTitle>
          <Icon
            iconName="alertCircle"
            stroke={COLORS.BLUE}
            fill={COLORS.LIGHT_BLUE}
          />
          <Link to="/issueDetail">
            <TitleText>이슈 제목</TitleText>
          </Link>
          <Labels>
            <Label
              labelName="레이블 이름"
              backgroundColor={GREYSCALE.BACKGROUND}
              textColor="어두운 색"
            />
          </Labels>
        </IssueTitle>
        <IssueInfo>
          <span>#이슈번호</span>
          <span>작성자 및 타임스탬프 정보</span>
          <Milestone>
            <Icon iconName="milestone" fill={GREYSCALE.LABEL} />
            마일스톤
          </Milestone>
        </IssueInfo>
      </IssueText>
      <IssueAssignee>
        <Icon iconName="userImageSmall" width={20} height={20} />
      </IssueAssignee>
    </IssueItemBox>
  );
}

const DefaultIssueItemBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start')}
  width: 1280px;
  height: 100px;
  background-color: ${GREYSCALE.OFF_WHITE};
  border: 1px solid ${GREYSCALE.LINE};
  border-top: none;
  padding: 24px 32px;
  gap: 32px;
`;

const LastIssueItemBox = styled(DefaultIssueItemBox)`
  border-radius: 0px 0px 16px 16px;
`;

const IssueText = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column')}
  gap: 8px;
  margin-top: -8px;
`;

const IssueTitle = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start', 'center')}
  gap: 8px;
`;

const TitleText = styled.h2`
  ${({ theme }) => theme.TYPOGRAPHY.LINK_MEDIUM}
  color: ${GREYSCALE.TITLE_ACTION};
  cursor: pointer;
`;

const Labels = styled.ul`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start', 'center')}
  gap: 8px;
`;

const IssueInfo = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start', 'center')}
  gap: 16px;
  color: ${GREYSCALE.LABEL};
`;

const Milestone = styled.p`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start', 'center')}
  gap: 8px;
`;

const IssueAssignee = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'center', 'center')}
  margin-left: auto;
`;

export default IssueItem;

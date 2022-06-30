import Icon, { IconNameType } from '@icon';
import { COLORS, GREYSCALE, PATH } from '@constants';
import { getRandomKey } from '@/utils';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { getTimeStamp } from '@/common/utils';
import CheckBox from '@issueListTable/CheckBox';
import { IssueStateType } from '@issueListTable/IssueList';
import { useIssueListContext } from '@/issueList/stores/IssueListProvider';
import Label from '@issueListTable/issueItem/Label';

type LabelColorType = {
  backgroundColor: string;
  textColor: string;
};

type LabelType = {
  name: string;
  color: LabelColorType;
};

type IssueItemProps = {
  id: string;
  title: string;
  createdTime: string;
  writer: string;
  labels: LabelType[] | null;
  milestoneName: string | null;
  isLast: boolean;
  status: IssueStateType;
};

type IconType = {
  iconName: IconNameType;
  stroke: string;
  fill: string;
};

type IssueIconType = {
  OPEN: IconType;
  CLOSE: IconType;
};

function IssueItem({
  id,
  title,
  createdTime,
  writer,
  labels,
  milestoneName,
  isLast,
  status
}: IssueItemProps) {
  const { state, dispatch } = useIssueListContext();

  const IssueItemBox = isLast ? LastIssueItemBox : DefaultIssueItemBox;
  const checkBoxType = state.selectedIssues[id] ? 'active' : 'initial';
  const issueIcon: IssueIconType = {
    OPEN: {
      iconName: 'alertCircle',
      stroke: COLORS.BLUE,
      fill: COLORS.LIGHT_BLUE
    },
    CLOSE: {
      iconName: 'archive',
      stroke: COLORS.PURPLE,
      fill: COLORS.LIGHT_PURPLE
    }
  };

  const handleCheckBoxClick = () => {
    if (state.selectedIssues[id]) {
      dispatch({ type: 'ITEM_UNCHECK', payload: { id } });
    } else {
      dispatch({ type: 'ITEM_CHECK', payload: { id } });
    }
  };

  const timeStamp = getTimeStamp(createdTime);

  return (
    <IssueItemBox>
      <CheckBox checkBoxType={checkBoxType} onClick={handleCheckBoxClick} />
      <IssueText>
        <IssueTitle>
          <Icon
            iconName={issueIcon[status].iconName}
            stroke={issueIcon[status].stroke}
            fill={issueIcon[status].fill}
          />
          <Link to={`/${PATH.ISSUE_DETAIL}`}>
            <TitleText>{title}</TitleText>
          </Link>
          <Labels>
            {!!labels &&
              labels.map(({ name, color }) => (
                <Label
                  key={getRandomKey()}
                  labelName={name}
                  backgroundColor={color.backgroundColor}
                  textColor={color.textColor}
                />
              ))}
          </Labels>
        </IssueTitle>
        <IssueInfo>
          <span>#{id}</span>
          <span>
            이 이슈가 {timeStamp}에, {writer}에 의해 작성되었습니다
          </span>
          {!!milestoneName && (
            <Milestone>
              <Icon iconName="milestone" fill={GREYSCALE.LABEL} />
              {milestoneName}
            </Milestone>
          )}
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

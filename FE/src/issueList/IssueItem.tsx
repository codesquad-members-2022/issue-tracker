import Icon from '@/assets/icons/Icon';
import { COLORS, GREYSCALE } from '@/constants';
import { getRandomKey } from '@/utils';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import CheckBox from './CheckBox';
import Label from './Label';

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
  labels: LabelType[];
  milestoneName: string;
  isSelected: boolean;
  isLast: boolean;
  updateIssueState: (id: string) => void;
};

function IssueItem({
  id,
  title,
  createdTime,
  writer,
  labels,
  milestoneName,
  isSelected,
  isLast,
  updateIssueState
}: IssueItemProps) {
  const IssueItemBox = isLast ? LastIssueItemBox : DefaultIssueItemBox;
  const checkBoxType = isSelected ? 'active' : 'initial';

  const handleClick = () => {
    updateIssueState(id);
  };

  const getTimeStamp = (createdTime: string) => {
    let timeStamp;

    const createdTimeDate = new Date(createdTime);
    const today = new Date();

    const createdDate = {
      year: createdTimeDate.getFullYear(),
      month: createdTimeDate.getMonth(),
      date: createdTimeDate.getDate(),
      hour: createdTimeDate.getHours(),
      min: createdTimeDate.getMinutes(),
      sec: createdTimeDate.getSeconds()
    };
    const todayDate = {
      year: today.getFullYear(),
      month: today.getMonth(),
      date: today.getDate(),
      hour: today.getHours(),
      min: today.getMinutes(),
      sec: today.getSeconds()
    };

    switch (true) {
      case createdDate.year !== todayDate.year:
        timeStamp = `${createdDate.year}년 ${createdDate.month + 1}월 ${
          createdDate.date
        }일`;
        break;
      case createdDate.month !== todayDate.month:
        timeStamp = `${createdDate.month + 1}월 ${createdDate.date}일`;
        break;
      case createdDate.date !== todayDate.date:
        timeStamp = `${todayDate.date - createdDate.date}일 전`;
        break;
      case createdDate.hour !== todayDate.hour:
        timeStamp = `${todayDate.hour - createdDate.hour}시간 전`;
        break;
      case createdDate.min !== todayDate.min:
        timeStamp = `${todayDate.min - createdDate.min}분 전`;
        break;
      default:
        timeStamp = `${todayDate.sec - createdDate.sec}초 전`;
    }

    return timeStamp;
  };
  const timeStamp = getTimeStamp(createdTime);

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
            <TitleText>{title}</TitleText>
          </Link>
          <Labels>
            {!!labels.length &&
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

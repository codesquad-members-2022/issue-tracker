import Dropdown from '@/components/common/Dropdown';
import { Icon } from '@/components/common/Icon';
import {
  $IssueListFilterDropDowns,
  $IssueListFIlterSelect
} from '@/components/IssueList/IssueListFilterDropDowns/style';
import UserProfile from '@/components/common/UserProfile';
import { useMemberListData } from '@/hooks/useMemberListData';
import { useLabelListData } from '@/hooks/useLabelListData';
import { useMilestoneListData } from '@/hooks/useMilestoneListData';
import {
  IMemberData,
  ILabelData,
  IMilestoneData
} from '@/components/IssueList/IssueListFilterDropDowns/type';

const radioIcon = {
  off: <Icon iconType="radioOff" />,
  on: <Icon iconType="radioOn" />
};

export default function IssueListFilterDropDowns() {
  const { status: memberDataStatus, data: memberList } = useMemberListData();
  const { status: labelDataStatus, data: labelList } = useLabelListData();
  const { status: milestoneDataStatus, data: milestoneList } = useMilestoneListData();

  const ISSUE_FILTERS_PROPS = [
    {
      indicatorName: '담당자',
      panelName: '담당자 필터',
      filterName: 'assignee'
    },
    {
      indicatorName: '레이블',
      panelName: '레이블 필터',
      filterName: 'label'
    },
    {
      indicatorName: '마일스톤',
      panelName: '마일스톤 필터',
      filterName: 'milestone'
    },
    {
      indicatorName: '작성자',
      panelName: '작성자 필터',
      filterName: 'author'
    }
  ];

  const getIssueFilterOptions = (filterName: string) => {
    switch (filterName) {
      case 'assignee':
        const optionData = memberList ? memberList?.data.data : [];
        return optionData.map(({ user_id, image_url }: IMemberData) => {
          return {
            children: (
              <$IssueListFIlterSelect>
                <UserProfile src={image_url} size="small" />
                {user_id}
              </$IssueListFIlterSelect>
            ),
            radio: radioIcon,
            value: user_id,
            filterCondition: {
              assignee: user_id
            }
          };
        });
      case 'label': {
        const optionData = labelList ? labelList.data.data : [];
        return optionData.map(({ name, description }: ILabelData) => {
          return {
            children: name,
            radio: radioIcon,
            value: description,
            filterCondition: {
              label: name
            }
          };
        });
      }
      case 'milestone': {
        const optionData = milestoneList ? milestoneList.data.data : [];
        return optionData.map(({ name, description }: IMilestoneData) => {
          return {
            children: name,
            radio: radioIcon,
            value: description,
            filterCondition: {
              milestone: name
            }
          };
        });
      }
      case 'author': {
        const optionData = memberList ? memberList?.data.data : [];
        return optionData.map(({ user_id, image_url }: IMemberData) => {
          return {
            children: (
              <$IssueListFIlterSelect>
                <UserProfile src={image_url} size="small" />
                {user_id}
              </$IssueListFIlterSelect>
            ),
            radio: radioIcon,
            value: user_id,
            filterCondition: {
              author: user_id
            }
          };
        });
      }
    }
  };

  return (
    <$IssueListFilterDropDowns>
      {ISSUE_FILTERS_PROPS.map(({ filterName, ...props }, idx) => (
        <Dropdown key={idx} left="right" options={getIssueFilterOptions(filterName)} {...props} />
      ))}
    </$IssueListFilterDropDowns>
  );
}

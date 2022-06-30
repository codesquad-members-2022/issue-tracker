import { useState } from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import * as S from './style';
import { assigneeList } from '@/recoil/atoms/assigneeData';
import { labelData as LabelData } from '@/recoil/atoms/LabelData';
import { milestoneData as MilestoneData } from '@/recoil/atoms/MilestoneData';
import { Text } from '@/components/common/Text';
import { ListModal } from '@/components/common/ListModal';
import { AssigneeListItem } from '@/components/User/AssigneeListItem';
import { LabelListItem } from '@/components/Label/LabelListItem';
import { MilestoneSideBarDetailListItem } from '@/components/Milestone/MilestoneSideBarDetailListItem';
import { addIssue } from '@/recoil/atoms/addIssue';
import { SideBarAssigneeItem } from '@/components/User/SideBarAssigneeItem';
import PLUS_IMOG from '@/assets/Icons/plus.svg';
import { LabelBadge } from '@/components/Label/LabelBadge';

type AssigneeHandler = React.MouseEvent<HTMLElement>;

export function AddIssueSideBar() {
  const assigneeData = useRecoilValue(assigneeList);
  const labelData = useRecoilValue(LabelData);
  const milestoneData = useRecoilValue(MilestoneData);
  const [issueState, setIssueState] = useRecoilState(addIssue);
  const [assigneeDetailSummary, setAssigneeDeatilSummary] = useState<
    {
      name: string;
      profileImage: string;
    }[]
  >([]);
  const [labelDetailSummary, setLabelDetailSummary] = useState<
    {
      name: string;
      description: string;
      color: string;
      textColor: string;
    }[]
  >([]);

  const handleAssigneeDetails = (e: AssigneeHandler) => {
    const clickedAssigneeName = (e.currentTarget as HTMLElement).dataset.id;

    if (assigneeDetailSummary.find(data => data.name === clickedAssigneeName)) {
      const newAssigneeDetailSummary = assigneeDetailSummary.filter(
        assignee => assignee.name !== clickedAssigneeName,
      );
      setIssueState({ ...issueState, assignee: newAssigneeDetailSummary });
      setAssigneeDeatilSummary(newAssigneeDetailSummary);
      return;
    }

    const targetAssignee = assigneeData.find(
      data => data.name === clickedAssigneeName,
    );

    if (!targetAssignee) {
      return;
    }

    const newAssigneeDetailSummary = [...assigneeDetailSummary, targetAssignee];
    setIssueState({
      ...issueState,
      assignee: [...issueState.assignee, targetAssignee],
    });
    setAssigneeDeatilSummary(newAssigneeDetailSummary);
  };

  const handleLabelDetails = (e: AssigneeHandler) => {
    const clickedLabelName = (e.currentTarget as HTMLElement).dataset.name;

    if (labelDetailSummary.find(data => data.name === clickedLabelName)) {
      const newLabelDetailSummary = labelDetailSummary.filter(
        label => label.name !== clickedLabelName,
      );

      setIssueState({ ...issueState, label: newLabelDetailSummary });
      setLabelDetailSummary(newLabelDetailSummary);
      return;
    }

    const targetLabel = labelData.find(data => data.name === clickedLabelName);

    if (!targetLabel) {
      return;
    }

    const newLabelDetailSummary = [...labelDetailSummary, targetLabel];
    setIssueState({
      ...issueState,
      label: [...issueState.label, targetLabel],
    });
    setLabelDetailSummary(newLabelDetailSummary);
  };

  const sideBarAssigneeList = assigneeData
    ? assigneeData.map(({ name, profileImage }) => (
        <AssigneeListItem
          assignee={name}
          assigneeProfileImg={profileImage}
          onClick={handleAssigneeDetails}
          userId={name}
        />
      ))
    : null;

  const sideBarLabelList = labelData
    ? labelData.map(({ color, name }) => (
        <LabelListItem color={color} name={name} onClick={handleLabelDetails} />
      ))
    : null;

  const sideBarMilestoneList = milestoneData
    ? milestoneData.map(({ title }) => (
        <MilestoneSideBarDetailListItem title={title} />
      ))
    : null;

  const sideBarAssigneeSummaryList = assigneeDetailSummary
    ? assigneeDetailSummary.map(({ name, profileImage }) => (
        <SideBarAssigneeItem name={name} profileImage={profileImage} />
      ))
    : null;

  const sideBarLabelSummaryList = labelDetailSummary
    ? labelDetailSummary.map(({ name, color }) => (
        <LabelBadge name={name} backgroundColor={color} />
      ))
    : null;

  console.log(issueState);

  return (
    <S.OptionSideBar>
      <S.OptionDetail>
        <summary>
          <S.titleSummary>
            <Text text="담당자" />
            <PLUS_IMOG />
          </S.titleSummary>
          {sideBarAssigneeSummaryList}
        </summary>
        <ListModal listTitle="담당자 추가" rightGap="10%">
          {sideBarAssigneeList}
        </ListModal>
      </S.OptionDetail>

      <S.OptionDetail>
        <summary>
          <S.titleSummary>
            <Text text="레이블" />
            <PLUS_IMOG />
          </S.titleSummary>
          {sideBarLabelSummaryList}
        </summary>
        <ListModal listTitle="라벨 추가" rightGap="10%">
          {sideBarLabelList}
        </ListModal>
      </S.OptionDetail>

      <S.OptionDetail>
        <summary>
          <S.titleSummary>
            <Text text="마일스톤" />
            <PLUS_IMOG />
          </S.titleSummary>
        </summary>
        <ListModal listTitle="마일스톤 추가" rightGap="10%">
          {sideBarMilestoneList}
        </ListModal>
      </S.OptionDetail>
    </S.OptionSideBar>
  );
}

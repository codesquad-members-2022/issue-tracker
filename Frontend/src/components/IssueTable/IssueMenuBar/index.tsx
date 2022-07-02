import axios, { AxiosResponse } from 'axios';
import React, { useContext, useState } from 'react';

import DropDown from '@/components/AssigneeDrop/DropDown';
import Button from '@/components/Button';

import MileStoneDropDown from '@/components/MileStoneDrop/MileStoneDropDown';
import { IssueMenuBarProps } from '@/components/IssueTable/type';
import { CheckedIssueIdContext } from '@/context/CheckedIssueIdProvider';
import { IssueType } from '@/context/IssueDataProvider';

import CheckBox from '../../CheckBox';
import * as S from './style';

const dropDownWidth = '128px';
const dropDownHeight = '40px';
const dropBoxWidth = '200px';
const dropDownTitle = ['담당자', '레이블', '마일스톤', '작성자'];
const itemsTitle = [
  '담당자 필터',
  '레이블 필터',
  '마일스톤 필터',
  '작성자 필터',
];

const assignees = [
  {
    id: 1,
    name: 'Jay',
    profileImageUrl:
      'https://avatars.githubusercontent.com/u/92818747?s=400&v=4',
  },
  {
    id: 2,
    name: 'Jun',
    profileImageUrl:
      'https://avatars.githubusercontent.com/u/92818747?s=400&v=4',
  },
];

const mileStones = [{ id: 1, title: 'PR 반영', deadline: '2022-06-30' }];

function IssueMenuBar({ issueData, setIssueStatus }: IssueMenuBarProps) {
  const { openIssueCount, closedIssueCount, issues } = issueData;
  const { checkedIssueId, dispatch } = useContext(CheckedIssueIdContext);
  // TODO: 전역상태 관리
  // const [assignees, setAssignees] = useState([]);
  // const [mileStones, setMileStones] = useState([]);

  // const handleClickAssignees = async () => {
  //   const id = 1;
  //   const response: AxiosResponse = await axios.get(
  //     `${process.env.SERVER_ENDPOINT}/api/issuegroup/${id}/issues/assignees`
  //   );
  //   setAssignees(response.data);
  // };

  // const handelClickMileStones = async () => {
  //   const id = 1;
  //   const response: AxiosResponse = await axios.get(
  //     `${process.env.SERVER_ENDPOINT}/api/issuegroup/${id}/milestones`
  //   );
  // };

  const idList = issues.map((issue: IssueType) => issue.id);
  const allChecked =
    idList.every((id) => checkedIssueId.has(id)) && idList.length !== 0;

  const handleChangeAllCheck = () => {
    if (allChecked) {
      return dispatch({ type: 'allClear' });
    }
    return dispatch({ type: 'allCheck', idList });
  };
  return (
    <S.Container>
      <S.Wrapper>
        <CheckBox checked={allChecked} onChange={handleChangeAllCheck} />
        <Button
          buttonStyle="mediumText"
          hasIcon
          iconType="alert"
          contents={`열린 이슈 (${openIssueCount})`}
          onClick={() => setIssueStatus('OPEN')}
        />
        <Button
          buttonStyle="mediumText"
          hasIcon
          iconType="archive"
          contents={`닫힌 이슈 (${closedIssueCount})`}
          onClick={() => setIssueStatus('CLOSED')}
        />
      </S.Wrapper>
      {checkedIssueId.size > 0 ? (
        <Button buttonStyle="sort" hasIcon={false} contents="상태 수정 ▽" />
      ) : (
        <S.Wrapper>
          <DropDown
            dropDownWidth={dropDownWidth}
            dropDownHeight={dropDownHeight}
            dropBoxWidth={dropBoxWidth}
            dropDownTitle={dropDownTitle[0]}
            itemsTitle={itemsTitle[0]}
            items={assignees}
          />
          <Button buttonStyle="sort" hasIcon={false} contents="레이블 ▽" />
          <MileStoneDropDown
            dropDownWidth={dropDownWidth}
            dropDownHeight={dropDownHeight}
            dropBoxWidth={dropBoxWidth}
            dropDownTitle={dropDownTitle[2]}
            itemsTitle={itemsTitle[2]}
            items={mileStones}
          />
          <DropDown
            dropDownWidth={dropDownWidth}
            dropDownHeight={dropDownHeight}
            dropBoxWidth={dropBoxWidth}
            dropDownTitle={dropDownTitle[3]}
            itemsTitle={itemsTitle[3]}
            items={assignees}
          />
        </S.Wrapper>
      )}
    </S.Container>
  );
}

export default IssueMenuBar;

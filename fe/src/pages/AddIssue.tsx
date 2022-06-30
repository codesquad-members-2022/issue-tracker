/* eslint-disable react/prop-types */
import React, { useRef, useState } from 'react';
import { useMutation, useQueryClient } from 'react-query';
import { useNavigate } from 'react-router-dom';

import { useGetLabelData } from 'api/label';
import useGetMilestoneData from 'api/milestone';
import useGetMembersData from 'api/member';

import styled from 'styled-components';
import AddIssueForm from 'components/Molecules/AddIssueForm';
import SideBar from 'components/Molecules/SideBar';
import Button from 'components/Atoms/Button';

import { UserTypes, LabelTypes, MilestoneTypes } from 'components/types';

import useInput from 'hooks/useInput';
import axios from 'axios';

const StyledDiv = styled.div`
  h1 {
    cursor: default;
    ${({ theme }) => theme.fontStyles.displayRegular};
  }

  .issue_form {
    display: flex;
    justify-content: space-around;
  }

  .issue_Buttons {
    display: flex;
    justify-content: flex-end;
    align-items: center;

    button + button {
      margin-left: 32px;
    }
  }
`;

const Divider = styled.div`
  width: 100%;
  height: 1px;
  margin: 32px 0;
  background: ${({ theme }) => theme.colors.line};
`;

interface NewIssueTypes {
  title: string;
  comment: string | null;
  writerId: number;
  assigneeIds: number[];
  labelIds: number[];
  milestoneId: number | null;
}

type FilterDataKeys = 'ASSIGN' | 'LABEL' | 'MILESTONE';
interface ContentListProps {
  ASSIGN: UserTypes[];
  LABEL: LabelTypes[];
  MILESTONE: MilestoneTypes[];
}

const AddIssue = () => {
  const navigate = useNavigate();

  const textareaRef = useRef<HTMLTextAreaElement>(null);
  const inputRef = useRef<HTMLInputElement>(null);

  const { isActive, isTyping, onChangeInput, onClickInput, onBlurInput } = useInput();

  const initState: ContentListProps = { ASSIGN: [], LABEL: [], MILESTONE: [] };
  const [contentList, setContentList] = useState(initState);
  const [isFilled, setIsFilled] = useState<boolean>(false);

  const queryClient = useQueryClient();

  const { isLoading: labelsDataIsLoading, data: labelData, error: labelsDataError } = useGetLabelData();
  const { isLoading: milestonesDataIsLoading, data: milestoneData, error: milestonesDataError } = useGetMilestoneData();
  const { isLoading: membersDataIsLoading, data: memberData, error: membersDataError } = useGetMembersData();

  const onChange = (event: React.FormEvent<HTMLInputElement>) => {
    onChangeInput(event);
    if (event.currentTarget.value) setIsFilled(true);
    else setIsFilled(false);
  };

  const addIssue = async (newIssue: NewIssueTypes): Promise<NewIssueTypes> => {
    const { data: addIssueData } = await axios.post<NewIssueTypes>(
      `${process.env.REACT_APP_SERVER_URL}/api/issues`,
      newIssue,
    );

    return addIssueData;
  };

  const { mutate } = useMutation(addIssue, {
    onSuccess: () => {
      queryClient.invalidateQueries('issueData');
    },
    onError: (error) => {
      console.log(error);
    },
  });

  const handleUploadButtonClick = () => {
    const userData = localStorage.getItem('userInfo');

    const issueInfo: NewIssueTypes = {
      title: inputRef.current?.value!,
      comment: textareaRef.current?.value || null,
      writerId: JSON.parse(userData!).id,
      assigneeIds: contentList.ASSIGN.map((list) => list.id),
      labelIds: contentList.LABEL.map((list) => list.id),
      milestoneId: contentList.MILESTONE.map((list) => list.id)[0] || null,
    };

    mutate(issueInfo);
    navigate('/');
  };

  if (labelsDataIsLoading || milestonesDataIsLoading || membersDataIsLoading) return <div>loading</div>;
  if (labelsDataError || milestonesDataError || membersDataError) return <div>error</div>;
  if (!labelData || !milestoneData || !memberData) return <div>데이터가 없습니다</div>;

  const sideBarData = {
    ASSIGN: memberData.members,
    LABEL: labelData.labels,
    MILESTONE: milestoneData.milestones,
  };

  const handleContentList = (event: React.MouseEvent<HTMLInputElement>) => {
    if (!(event.target instanceof HTMLInputElement)) return;
    const { checked } = event.target;
    const { type, id } = event.target.dataset;

    const key = type as FilterDataKeys;
    const sideBarFilterData: (UserTypes | LabelTypes | MilestoneTypes)[] = sideBarData[key];
    const [checkedData] = sideBarFilterData.filter((el) => el.id === Number(id));

    if (checked) {
      setContentList({ ...contentList, [key]: [...contentList[key], checkedData] });
    } else {
      const contentListFilterData: (UserTypes | LabelTypes | MilestoneTypes)[] = contentList[key];

      setContentList({
        ...contentList,
        [key]: contentListFilterData.filter((el) => el.id !== Number(id)),
      });
    }
  };

  const sideBarList = [
    {
      id: 1,
      type: 'ASSIGN',
      indicatorLabel: '담당자',
      dropdownTitle: '담당자 추가',
      dropdownList: memberData.members,
      contentList: contentList.ASSIGN,
      clickHandler: handleContentList,
    },
    {
      id: 2,
      type: 'LABEL',
      indicatorLabel: '레이블',
      dropdownTitle: '레이블 추가',
      dropdownList: labelData.labels,
      contentList: contentList.LABEL,
      clickHandler: handleContentList,
    },
    {
      id: 3,
      type: 'MILESTONE',
      indicatorLabel: '마일스톤',
      dropdownTitle: '마일스톤 추가',
      dropdownList: milestoneData.milestones,
      contentList: contentList.MILESTONE,
      clickHandler: handleContentList,
    },
  ];

  return (
    <StyledDiv>
      <h1>새로운 이슈 작성</h1>
      <Divider />
      <div className="issue_form">
        <AddIssueForm
          isActive={isActive}
          isTyping={isTyping}
          inputMaxLength={15}
          inputPlaceholder="제목"
          inputSize="LARGE"
          inputType="text"
          inputRef={inputRef}
          onBlur={onBlurInput}
          onChange={onChange}
          onClick={onClickInput}
          textareaMaxLength={600}
          textareaPlaceholder="코멘트를 입력하세요"
          textareaSize="LARGE"
          textareaRef={textareaRef}
        />
        <SideBar isEditer sideBarList={sideBarList} />
      </div>
      <Divider />
      <div className="issue_Buttons">
        <Button
          buttonStyle="NO_BORDER"
          iconInfo={{ icon: 'XSquare' }}
          label="작성취소"
          size="SMALL"
          HandleOnClick={() => navigate(-1)}
        />
        <Button
          buttonStyle="STANDARD"
          disabled={!isFilled}
          label="완료"
          size="MEDIUM"
          HandleOnClick={handleUploadButtonClick}
        />
      </div>
    </StyledDiv>
  );
};

export default AddIssue;

import * as I from 'design/icons';
import * as S from 'components/common/Sidebar/styled/dropdown';

import { ReactNode, useState } from 'react';
import { useRecoilState } from 'recoil';

import {
  accountsData,
  AccountType,
  labelsData,
  LabelType,
  mileStonesData,
  MileStoneType,
} from 'data';
import { keyMaker } from 'utils/util';
import { newIssueState } from 'store/newIssue';
import Label from '../Common';

type DropdownType = {
  subject: string;
};

function DropDown({ subject }: DropdownType) {
  const [isClicked, setIsClicked] = useState(false);
  const [newIssue, setNewIssue] = useRecoilState(newIssueState);

  function setAssignee(content: AccountType) {
    if (newIssue.assignees[0].name === '')
      setNewIssue({
        ...newIssue,
        assignees: [
          {
            name: content.name,
            profileImage: content.profileImage,
            email: '',
          },
        ],
      });
    else {
      setNewIssue({
        ...newIssue,
        assignees: [
          ...newIssue.assignees,
          {
            name: content.name,
            profileImage: content.profileImage,
            email: '',
          },
        ],
      });
    }
  }
  function setLabels(content: LabelType) {
    if (newIssue.labels[0].title === '')
      setNewIssue({
        ...newIssue,
        labels: [
          {
            title: content.title,
            description: content.description,
            color: content.color,
          },
        ],
      });
    else {
      setNewIssue({
        ...newIssue,
        labels: [
          ...newIssue.labels,
          {
            title: content.title,
            description: content.description,
            color: content.color,
          },
        ],
      });
    }
  }
  function setMileStone(content: MileStoneType) {
    setNewIssue({
      ...newIssue,
      mileStone: {
        title: content.title,
        description: content.description,
        dueDate: content.dueDate,
        progress: content.progress,
        openedIssue: content.openedIssue,
        closedIssue: content.closedIssue,
      },
    });
  }
  function getDropDownContents(subject: string) {
    let contentsData;
    let content: ReactNode;
    switch (subject) {
      case '담당자':
        contentsData = accountsData;
        content = contentsData.map((content, idx) => {
          const key = keyMaker();
          return (
            <S.DropDownList
              key={key}
              idx={idx}
              onClick={() => {
                setAssignee(content);
              }}
            >
              <S.DropDownContent>
                <S.SmallAcountImg src={content.profileImage} />
                {content.name}
              </S.DropDownContent>
              <I.offCheckCircle />
            </S.DropDownList>
          );
        });
        return content;
      case '레이블':
        contentsData = labelsData;
        content = contentsData.map((content, idx) => {
          const key = keyMaker();
          return (
            <S.DropDownList
              key={key}
              idx={idx}
              onClick={() => {
                setLabels(content);
              }}
            >
              <S.DropDownContent>
                <Label title={content.title} color={content.color} />
              </S.DropDownContent>
              <I.offCheckCircle />
            </S.DropDownList>
          );
        });
        return content;
      case '마일스톤':
        contentsData = mileStonesData;
        content = contentsData.map((content, idx) => {
          const key = keyMaker();
          return (
            <S.DropDownList
              key={key}
              idx={idx}
              onClick={() => {
                setMileStone(content);
              }}
            >
              <S.DropDownContent>{content.title}</S.DropDownContent>
              <I.offCheckCircle />
            </S.DropDownList>
          );
        });
        return content;
      default:
        throw new Error();
    }
  }
  function toggleDropdown() {
    setIsClicked(!isClicked);
  }
  return (
    <S.PlusFigure
      onClick={() => {
        toggleDropdown();
      }}
    >
      <I.plus />
      <S.DropdownPanel isClicked={isClicked}>
        <S.DropDownTop>{subject} 추가</S.DropDownTop>
        <S.DropDownBottom>{getDropDownContents(subject)}</S.DropDownBottom>
      </S.DropdownPanel>
    </S.PlusFigure>
  );
}

export default DropDown;

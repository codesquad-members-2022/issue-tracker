/* eslint-disable array-element-newline */
import React from 'react';
import CommentArea from 'src/components/commentArea/CommentArea';
import CommentHeader from 'src/components/commentArea/CommentHeader';
import FileAttachButton from 'src/components/fileAttachButton/FileAttachButton';
import IssueSideBarForm from 'src/components/issueSideBarForm/IssueSideBarForm';
import SubmitButton from 'src/components/submitButton/SubmitButton';
import Title from 'src/components/title/Title';
import {
  AddCommentArea,
  AddCommentAreaFooter,
  AddCommentAreaMain,
  AddIssuePageBox,
  AddCommentAreaBox,
  SideBar,
} from './AddIssuePage.styled';

const createKey = (value: string, key: number) => `value:${value}-key:${key}`;

const sideBarInfo = [
  {
    title: 'Assignees',
    defaultContent: 'No one-assign yourself',
  },
  {
    title: 'Labels',
    defaultContent: 'None yet',
    style: 'display:flex; flex-direction:row',
  },
  {
    title: 'Projects',
    defaultContent: 'None yet',
  },
  {
    title: 'Milestone',
    defaultContent: 'No milestone',
  },
  {
    title: 'Development',
    defaultContent: 'Shows branches and pull requests linked to this issue.    ',
  },
  {
    title: 'Helpful resources',
    defaultContent: 'GitHub Community Guidelines',
  },
];

export default function AddIssuePage() {
  return (
    <AddIssuePageBox>
      <AddCommentAreaBox width="838px" height="360px">
        <AddCommentArea>
          <Title width="100%" height="30px" />
          <AddCommentAreaMain>
            <CommentHeader />
            <CommentArea width="100%" height="200px" />
          </AddCommentAreaMain>
          <AddCommentAreaFooter>
            <FileAttachButton></FileAttachButton>
            <SubmitButton width="148px" height="30px" />
          </AddCommentAreaFooter>
        </AddCommentArea>
      </AddCommentAreaBox>
      <SideBar>
        {sideBarInfo.map(({ title, defaultContent, style }, idx) => (
          <IssueSideBarForm
            key={createKey(title, idx)}
            title={title}
            defaultContent={defaultContent}
            contentStyle={style || ''}
          ></IssueSideBarForm>
        ))}
      </SideBar>
    </AddIssuePageBox>
  );
}

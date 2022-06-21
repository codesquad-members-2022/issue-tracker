import React from 'react';
import { IssueSideBarFormBox, IssueSideBarFormContent, IssueSideBarHeader } from './IssueSideBarForm.styled';

type IssueSideBarFormType = {
  title: string;
  contentStyle: string;
  defaultContent: string;
};
export default function IssueSideBarForm({
  title,
  contentStyle = '',
  defaultContent = 'None yet',
}: IssueSideBarFormType) {
  return (
    <IssueSideBarFormBox>
      <IssueSideBarHeader>
        <div>{title}</div>
      </IssueSideBarHeader>
      <IssueSideBarFormContent styles={contentStyle}>{defaultContent}</IssueSideBarFormContent>
    </IssueSideBarFormBox>
  );
}

import React from 'react';
import { TitleInput } from './titleInput';
import { CommentInput } from './commentInput';

export const AddIssueInputs = (): JSX.Element => {
  return (
    <>
      <TitleInput />
      <CommentInput />
    </>
  );
};

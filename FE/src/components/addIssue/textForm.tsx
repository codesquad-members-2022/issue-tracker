import React from 'react';
import { TitleInput } from './titleInput';
import { CommentInput } from './commentInput';

export const TextForm = (): JSX.Element => {
  return (
    <>
      <TitleInput />
      <CommentInput />
    </>
  );
};

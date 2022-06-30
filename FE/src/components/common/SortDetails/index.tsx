import React from 'react';
import * as S from './style';

type ChildrenType = {
  children?: React.ReactNode;
};

export function SortDetails({ children }: ChildrenType) {
  return <S.SortDetails>{children}</S.SortDetails>;
}

import React, { useContext } from 'react';

import { IssueItemProps } from '@/components/IssueTable/type';
import Label from '@/components/Label';
import UserImg from '@/components/UserImg';
import { CheckedIssueIdContext } from '@/context/CheckedIssueIdProvider';

import CheckBox from '../../CheckBox';
import * as S from './style';

export default function IssueItem({ issue }: IssueItemProps) {
  const { id, title, createdAt, milestone, labels, author } = issue;
  const { checkedIssueId, dispatch } = useContext(CheckedIssueIdContext);
  const checked = checkedIssueId.has(id);

  const handleChangeCheck = () => {
    if (checked) {
      dispatch({ type: 'cancel', id });
      return;
    }
    dispatch({ type: 'check', id });
  };
  return (
    <S.Container>
      <S.Wrapper1>
        <CheckBox checked={checked} onChange={handleChangeCheck} />
        <S.Wrapper2>
          <S.Wrapper3>
            <S.Title>{title}</S.Title>
            {labels.map((label) => (
              <Label
                key={label.id}
                contents={label.name}
                size="small"
                labelStyle="light"
                background={`#${label.color}`}
              />
            ))}
          </S.Wrapper3>
          <S.Wrapper3>
            <S.Content>#{id}</S.Content>
            <S.Content>{createdAt}</S.Content>
            <S.Content>{milestone.title}</S.Content>
          </S.Wrapper3>
        </S.Wrapper2>
      </S.Wrapper1>
      <UserImg img={author.profileImageUrl} size="small" />
    </S.Container>
  );
}

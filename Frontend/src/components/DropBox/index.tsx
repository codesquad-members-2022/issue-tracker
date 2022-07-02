/* eslint-disable no-unused-vars */
import axios, { AxiosResponse } from 'axios';
import { useContext } from 'react';

import { IssueDataContext } from '@/context/IssueDataProvider';

import * as S from './style';

interface Item {
  filter: string;
  description: string;
}

interface DropBoxProps {
  itemsTitle: string;
  items: Item[];
}

export default function DropBox({ itemsTitle, items }: DropBoxProps) {
  const { setIssueData } = useContext(IssueDataContext);
  const handleClickDropDown = (keyword: string) => {
    getFilterdIssues(keyword);
  };

  const getFilterdIssues = async (keyword: string) => {
    const token = localStorage.getItem('token');
    const id = 1;
    const response: AxiosResponse = await axios.get(
      `${process.env.SERVER_ENDPOINT}/api/issuegroup/${id}/issues`,
      {
        params: { q: keyword },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setIssueData(response.data);
  };

  return (
    <S.ItemContainer>
      {itemsTitle}
      {items.map(({ description, filter }) => (
        <S.Item
          key={`issue-filter-${description}`}
          onClick={() => handleClickDropDown(filter)}
        >
          {description}
        </S.Item>
      ))}
    </S.ItemContainer>
  );
}

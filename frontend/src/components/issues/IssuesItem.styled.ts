import { flexLayoutMixin } from 'src/utils/utils';
import styled from 'styled-components';

export const IssuesItemBox = styled.div`
  ${flexLayoutMixin('row', 'space-between')}
  width: 100%;
  color-scheme: dark;
  padding: 16px;
  gap: 15px;
  fill: #57ab5a;
  &:hover {
    background-color: #2d333b;
  }
`;

export const ItemMain = styled.div`
  ${flexLayoutMixin('column', 'center')};
  gap: 10px;
`;

export const Title = styled.span`
  font-weight: 600;
  color: #adbac7;
  &:hover {
    color: #4c8cdb;
    cursor: pointer;
  }
`;

export const OpenedBy = styled.span`
  color: #768390;
  font-size: 0.75rem;
`;

export const FlexBox = styled.div`
  flex-grow: 1;
`;

export const Assignee = styled.div`
  padding: 0 20px;
  img {
    width: 20px;
    height: 20px;
    border-radius: 999px;
  }
  &:hover {
    cursor: pointer;
  }
`;
export const Review = styled.div`
  ${flexLayoutMixin('row', 'center', 'flex-start')}
  fill: #768390;
  padding: 0 20px;
  gap: 2px;
  line-height: 17px;
  color: #768390;
  &:hover {
    fill: #4c8cdb;
    color: #4c8cdb;
    cursor: pointer;
  }
`;

export const ReviewNum = styled.span`
  font-size: 0.75rem;
`;

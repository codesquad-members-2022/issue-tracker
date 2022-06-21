import { flexLayoutMixin } from 'src/utils/utils';
import styled from 'styled-components';

type AddIssuePageType = {
  width?: string;
  height?: string;
};

export const AddIssuePageBox = styled.div<AddIssuePageType>`
  width: ${({ width }) => width};
  height: ${({ height }) => height};
  ${flexLayoutMixin('row', 'center', '')};
`;

export const AddCommentAreaBox = styled.div<AddIssuePageType>`
  width: ${({ width }) => width};
  height: ${({ height }) => height};
  border: 1px solid #30363d;
  border-radius: 5px;
  margin-right: 24px;
`;

export const AddCommentArea = styled.div`
  padding: 8px;
`;

export const AddCommentAreaMain = styled.div`
  margin-top: 10px;
`;

export const AddCommentAreaFooter = styled.div`
  ${flexLayoutMixin('row', 'space-between', '')};
  margin-top: 10px;
`;

export const SideBar = styled.div``;

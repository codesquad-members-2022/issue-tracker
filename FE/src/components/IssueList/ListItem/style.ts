import styled from 'styled-components';
import { I$Text } from '@/components/IssueList/ListItem/type';

const $ListItem = styled.li`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 100px;
  padding: 0 60px 0 30px;
`;

const $Contents = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  gap: 10px;
  flex: 1;
`;

const $Title = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-start;
  gap: 9px;
`;

const $Info = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-start;
  gap: 16px;
`;

const $Text = styled.div<I$Text>`
  color: ${({ theme, size }) => (size === 'large' ? theme.COLOR.title : theme.COLOR.label)};
  font-size: ${({ theme, size }) =>
    size === 'large' ? theme.FONT.SIZE.MEDIUM : theme.FONT.SIZE.BASE};
`;

export { $ListItem, $Contents, $Title, $Info, $Text };

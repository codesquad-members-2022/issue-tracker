/* eslint-disable no-unused-vars */

import Button from '@/components/Button';
import FilterSeatchBar from '@/components/FilterSearchBar';
import Tap from '@/components/Tap';
import { IssueDataProvider } from '@/context/IssueDataProvider';
import useMouse from '@/hooks/useMouse';

import Header from '../../components/Header';
import IssueTable from '../../components/IssueTable';
import * as S from './style';

const dropDownWidth = '128px';
const dropDownHeight = '40px';
const dropBoxWidth = '200px';
const dropDownTitle = ['필터', '상태수정'];
const itemsTitle = '이슈필터';

const items = [
  { description: '열린 이슈', filter: 'is:open' },
  { description: '내가 작성한 이슈', filter: 'author:@me' },
  { description: '나에게 작성한 이슈', filter: 'assigned:@me' },
  { description: '내가 댓글을 남긴 이슈', filter: 'commented:@me' },
  { description: '닫힌 이슈', filter: 'is:closed' },
];

export default function Main() {
  const {
    isMouseOvered,
    isActive,
    handleMouseOver,
    handleMouseOut,
    handleMouseDown,
    handleMouseUp,
  } = useMouse(false);

  return (
    <>
      <Header />
      <IssueDataProvider>
        <S.Container>
          <S.MenuBar>
            <FilterSeatchBar
              dropDownWidth={dropDownWidth}
              dropDownHeight={dropDownHeight}
              dropBoxWidth={dropBoxWidth}
              dropDownTitle={dropDownTitle[0]}
              itemsTitle={itemsTitle}
              items={items}
            />
            <S.Menus>
              <S.TapContainer
                onMouseOver={handleMouseOver}
                onMouseOut={handleMouseOut}
                onMouseDown={handleMouseDown}
                onMouseUp={handleMouseUp}
                isMouseOvered={isMouseOvered}
                isActive={isActive}
              >
                <Tap iconType="label" title="레이블" borderDirection="left" />
                <Tap
                  iconType="mileStone"
                  title="마일스톤"
                  borderDirection="right"
                />
              </S.TapContainer>
              <Button buttonStyle="smallStandard" hasIcon contents="이슈작성" />
            </S.Menus>
          </S.MenuBar>
          <IssueTable />
        </S.Container>
      </IssueDataProvider>
    </>
  );
}

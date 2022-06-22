import styled from 'styled-components';
import { COLOR } from 'styles/color';
import FONT from 'styles/font';
import Button from '../common/Button/Button';
import DropdownBtn from '../common/DropdownBtn';
import Icon from '../common/Icon';
import Checkbox from './Checkbox';

function IssueHeader() {
  return (
    <Wrapper>
      <LeftBtns>
        <Checkbox />
        <Buttons>
          <OpenedIssueBtn />
          <ClosedIssueBtn />
        </Buttons>
      </LeftBtns>
      <RightBtns>
        <DropdownBtn name="담당자" startLocation="RIGHT">
          <div>BB</div>
        </DropdownBtn>
        <DropdownBtn name="레이블" startLocation="LEFT">
          <div>나는 모달 ㅋㅋ</div>
        </DropdownBtn>
        <DropdownBtn name="마일스톤" startLocation="RIGHT">
          <div>나는 모달</div>
        </DropdownBtn>
        <DropdownBtn name="작성자" startLocation="RIGHT">
          <div>나는 모달</div>
        </DropdownBtn>
      </RightBtns>
    </Wrapper>
  );
}

// TODO: OpenedIssueBtn, ClosedIssueBtn 중복 로직 하나의 컴포넌트로 합치기
function OpenedIssueBtn() {
  return (
    <Button
      width="fit-contents"
      template="MEDIUM_TEXT"
      backgroundColor={{ initial: 'transparent' }}
      fontStyles={{
        fontColor: {
          initial: COLOR.GREY[500],
          active: COLOR.BLACK,
          hover: COLOR.GREY[600],
          disabled: COLOR.GREY[400],
        },
        fontWeight: FONT.WEIGHT.BOLD,
      }}
      borderStyle="border: 0; padding: 0;"
    >
      <TitleWrapper>
        <Icon icon="alertCircle" />
        열린 이슈 (3)
      </TitleWrapper>
    </Button>
  );
}

function ClosedIssueBtn() {
  return (
    <Button
      width="fit-contents"
      template="MEDIUM_TEXT"
      backgroundColor={{ initial: 'transparent' }}
      fontStyles={{
        fontColor: {
          initial: COLOR.GREY[400],
          active: COLOR.BLACK,
          hover: COLOR.GREY[600],
          disabled: COLOR.GREY[400],
        },
        fontWeight: FONT.WEIGHT.BOLD,
      }}
      borderStyle="border: 0; padding: 0;"
    >
      <TitleWrapper>
        <Icon icon="archive" />
        닫힌 이슈 (0)
      </TitleWrapper>
    </Button>
  );
}

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  overflow: visible;
  justify-content: space-between;
`;

const Buttons = styled.div`
  display: flex;
  gap: 24px;
`;

const TitleWrapper = styled.div`
  width: fit-content;
  display: flex;
  align-items: center;
  gap: 7px;
`;

const LeftBtns = styled.div`
  display: flex;
`;

const RightBtns = styled.div`
  display: flex;
  gap: 26px;
`;

export default IssueHeader;

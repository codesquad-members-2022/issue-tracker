import styled from 'styled-components';
import Button from 'components/common/Button/Button';
import Icon from 'components/common/Icon';
import { COLOR } from 'styles/color';
import FONT from 'styles/font';

function MilestoneHeader() {
  return (
    <Wrapper>
      <OpenedMilestoneBtn />
      <ClosedMilestoneBtn />
    </Wrapper>
  );
}

export default MilestoneHeader;

function OpenedMilestoneBtn() {
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
        <Icon icon="milestone" stroke="inherit" fill={COLOR.BLACK} />
        열린 마일스톤 (3)
      </TitleWrapper>
    </Button>
  );
}

function ClosedMilestoneBtn() {
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
        닫힌 마일스톤 (0)
      </TitleWrapper>
    </Button>
  );
}

const Wrapper = styled.div`
  display: flex;

  > :not(:last-child) {
    margin-right: 24px;
  }
`;

const TitleWrapper = styled.div`
  width: fit-content;
  display: flex;
  align-items: center;
  gap: 7px;
`;

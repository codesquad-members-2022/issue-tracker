import styled from 'styled-components';
import ButtonLink from 'components/common/Button/ButtonLink';
import Icon from 'components/common/Icon';
import LinearGraph from 'components/common/LinearGraph';
import Text from 'components/common/Text';
import { COLOR } from 'styles/color';
import FONT from 'styles/font';

function MilestoneItem({ title, dueDate, desc, totalCount, doneCount, id }: MilestoneItemProps) {
  return (
    <Wrapper>
      <div className="item-wrap">
        <div className="item">
          <IconText>
            <Icon icon="milestone" stroke="inherit" fill={COLOR.BLUE[200]} />
            <Text weight={FONT.WEIGHT.BOLD}>{title}</Text>
          </IconText>
          <IconText>
            <Icon icon="calendar" stroke={COLOR.GREY[500]} />
            <Text color={COLOR.GREY[500]}>{dueDate}</Text>
          </IconText>
        </div>

        <div>
          <Text color={COLOR.GREY[500]}>{desc}</Text>
        </div>
      </div>
      <div className="item-wrap">
        <div className="button-area">
          <ButtonLink
            template="SMALL_TEXT"
            backgroundColor={{ initial: COLOR.WHITE }}
            fontStyles={{
              fontColor: {
                initial: COLOR.GREY[500],
              },
              fontSize: FONT.SIZE.X_SMALL,
              fontWeight: FONT.WEIGHT.BOLD,
            }}
            to={`/${id}`}
          >
            <Icon icon="calendar" stroke={COLOR.GREY[500]} />
            닫기
          </ButtonLink>
          <ButtonLink
            template="SMALL_TEXT"
            backgroundColor={{ initial: COLOR.WHITE }}
            fontStyles={{
              fontColor: {
                initial: COLOR.GREY[500],
              },
              fontSize: FONT.SIZE.X_SMALL,
              fontWeight: FONT.WEIGHT.BOLD,
            }}
            to={`/${id}`}
          >
            <Icon icon="edit" stroke={COLOR.GREY[500]} />
            편집
          </ButtonLink>
          <ButtonLink
            template="SMALL_TEXT"
            backgroundColor={{ initial: COLOR.WHITE }}
            fontStyles={{
              fontColor: {
                initial: COLOR.RED[200],
              },
              fontSize: FONT.SIZE.X_SMALL,
              fontWeight: FONT.WEIGHT.BOLD,
            }}
            to="/"
          >
            <Icon icon="trash" stroke={COLOR.RED[200]} />
            삭제
          </ButtonLink>
        </div>
        <div className="item graph-area">
          <LinearGraph totalCount={totalCount} doneCount={doneCount} />
          <div className="desc">
            <div>
              <DescText color={COLOR.GREY[500]}>
                {totalCount ? (doneCount / totalCount) * 100 : 0}%
              </DescText>
            </div>
            <div>
              <DescText color={COLOR.GREY[500]}>
                열린 이슈{totalCount - doneCount ? totalCount - doneCount : 0}
              </DescText>
              <DescText color={COLOR.GREY[500]}>닫힌 이슈 {doneCount || 0}</DescText>
            </div>
          </div>
        </div>
      </div>
    </Wrapper>
  );
}

export default MilestoneItem;

const Wrapper = styled.div`
  padding: 1rem 0;
  display: flex;
  justify-content: space-between;

  .item-wrap {
    display: flex;
    padding: 5px 0;
    flex-direction: column;
    justify-content: space-between;
  }

  .item {
    display: flex;
    gap: 10px;
  }

  .button-area {
    display: flex;
    justify-content: flex-end;
    margin-left: 10px;

    a {
      align-items: flex-start;
      justify-content: flex-end;
    }
  }

  .graph-area {
    flex-direction: column;

    .desc {
      display: flex;
      justify-content: space-between;
    }
  }
`;

const IconText = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

const DescText = styled(Text)`
  font-size: ${FONT.SIZE.X_SMALL};

  &:not(:last-child) {
    margin-right: 10px;
  }
`;

type MilestoneItemProps = {
  title: string;
  dueDate: string;
  desc: string;
  totalCount: number;
  doneCount: number;
  id: string;
};

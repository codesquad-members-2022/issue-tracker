import styled from 'styled-components';
import { GREYSCALE } from '@/constants';
import DropDownCheckBoxItem from './\bDropDownCheckBoxItem';

type DropDownPanelProps = {
  items: any; // 배열안에 객체가 있는 타입, 수정해야함
  showCheckBox: boolean;
  filterName: string;
  positionX?: number;
  positionY?: number;
};

function DropDownPanel({
  items,
  showCheckBox,
  filterName,
  positionX,
  positionY
}: DropDownPanelProps) {
  return (
    <DropDownPanelBox positionX={positionX} positionY={positionY}>
      <PanelTitle>{filterName}</PanelTitle>
      <ul>
        {items.map(({ id, isChecked, label, color, image }) => (
          <DropDownCheckBoxItem
            key={id}
            isChecked={isChecked}
            showCheckBox={showCheckBox}
            label={label}
            color={color}
            image={image}
          />
        ))}
      </ul>
    </DropDownPanelBox>
  );
}

const DropDownPanelBox = styled.div`
  position: absolute;
  top: ${({ positionX }) => `${positionX}px`};
  left: ${({ positionY }) => `${positionY}px`};
  width: 240px;
  border: 1px solid ${GREYSCALE.LINE};
  border-radius: 16px;
  overflow: hidden;
  z-index: 500;
`;

const PanelTitle = styled.div`
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_MEDIUM};
  padding: 8px 16px;
  background-color: ${GREYSCALE.BACKGROUND};
`;

export default DropDownPanel;

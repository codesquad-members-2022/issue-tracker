import styled from 'styled-components';
import { GREYSCALE } from '@/constants';
import { getRandomKey } from '@/utils';
import DropDownCheckBoxItem from './DropDownCheckBoxItem';

type DropDownPanelProps = {
  items: any; // 배열안에 객체가 있는 타입, 수정해야함
  showCheckBox: boolean;
  filterName: string;
};

function DropDownPanel({
  items,
  showCheckBox,
  filterName
}: DropDownPanelProps) {
  return (
    <DropDownPanelBox>
      <PanelTitle>{filterName}</PanelTitle>
      <ul>
        {items.map(({ id, isChecked, label, color, image }) => (
          <DropDownCheckBoxItem
            key={getRandomKey()}
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
  width: 240px;
  border: 1px solid ${GREYSCALE.LINE};
  border-radius: 16px;
  overflow: hidden;
`;

const PanelTitle = styled.div`
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_MEDIUM};
  padding: 8px 16px;
  background-color: ${GREYSCALE.BACKGROUND};
`;

export default DropDownPanel;

import styled from 'styled-components';
import { GREYSCALE } from '@/constants';
import Icon from '@/assets/icons/Icon';

type DropDownCheckBoxItemProps = {
  isChecked: boolean;
  showCheckBox: boolean;
  label: string;
  color?: string;
  image?: string;
};

function DropDownCheckBoxItem({
  isChecked,
  showCheckBox,
  label,
  color,
  image
}: DropDownCheckBoxItemProps) {
  const CheckBoxIcon = isChecked ? (
    <Icon iconName="checkOnCircle" />
  ) : (
    <Icon iconName="checkOffCircle" />
  );

  return (
    <Item>
      {color && <ColorTag bgColor={color} />}
      {image && <ImageTag imageUrl={image} />}
      <Label>{label}</Label>
      {showCheckBox && CheckBoxIcon}
    </Item>
  );
}

const Item = styled.li`
  ${({ theme }) =>
    theme.LAYOUT.flexLayoutMixin('row', 'space-between', 'center')}
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_SMALL}
  padding: 8px 16px;
  height: 44px;
  border-top: 1px solid ${GREYSCALE.LINE};
  background-color: ${GREYSCALE.OFF_WHITE};
  text-align: left;
  cursor: pointer;

  &:hover {
    font-weight: 500;
  }
`;

const Label = styled.p`
  width: 160px;
`;

const Tag = styled.div`
  width: 20px;
  height: 20px;
  border-radius: 20px;
  border: 1px solid ${GREYSCALE.LINE};
  margin-right: 8px;
`;

const ImageTag = styled(Tag)`
  background-image: url(${({ imageUrl }) => imageUrl});
`;

const ColorTag = styled(Tag)`
  background-color: ${({ bgColor }) => bgColor};
`;

export default DropDownCheckBoxItem;

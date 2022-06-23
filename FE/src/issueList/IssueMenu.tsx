import Icon from '@/assets/icons/Icon';
import { GREYSCALE } from '@/constants';
import styled from 'styled-components';
import icons from '@/assets/icons/svgs';

type IssueMenuProps = {
  icon: keyof typeof icons;
  menuName: string;
  count: number;
  isCurrent: boolean;
};

function IssueMenu({ icon, menuName, count, isCurrent }: IssueMenuProps) {
  const Menu = isCurrent ? CurrentMenu : DefaultMenu;
  const color = isCurrent ? GREYSCALE.TITLE_ACTION : GREYSCALE.LABEL;

  return (
    <IssueMenuBox>
      <Icon iconName={icon} width={14} height={14} stroke={color} />
      <Menu>
        {menuName}({count})
      </Menu>
    </IssueMenuBox>
  );
}

const IssueMenuBox = styled.li`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start', 'center')}
  gap: 5px;
  cursor: pointer;
`;

const DefaultMenu = styled.p`
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
  color: ${GREYSCALE.LABEL};
`;

const CurrentMenu = styled(DefaultMenu)`
  color: ${GREYSCALE.TITLE_ACTION};
`;

export default IssueMenu;

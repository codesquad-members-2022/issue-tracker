import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { Text } from '../common/Text';
import MILESTONE_ICON from '@/assets/Icons/milestone.svg';

export const NavItemWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 225px;
  gap: 10px;
  padding: 6px 32.5px;
`;

const LinkWrapper = styled.div`
  display: flex;
  align-items: center;
`;

type MilestoneNavItemProps = {
  milestoneLength: number;
};

export function MilestonNavItem({ milestoneLength }: MilestoneNavItemProps) {
  return (
    <LinkWrapper>
      <Link to="/milestones">
        <NavItemWrapper>
          <MILESTONE_ICON />
          <Text text="마일스톤" color="label" fontWeight="bold" />
          <Text text={`(${milestoneLength})`} color="label" />
        </NavItemWrapper>
      </Link>
    </LinkWrapper>
  );
}

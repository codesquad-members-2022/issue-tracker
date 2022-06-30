import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { Text } from '../common/Text';
import LABEL_ICON from '@/assets/Icons/tag.svg';

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
  border-right: 1px solid ${({ theme }) => theme.color.line};
`;

type LabelNavItemProps = {
  labelLength: number;
};

export function LabelNavItem({ labelLength }: LabelNavItemProps) {
  return (
    <LinkWrapper>
      <Link to="/labels">
        <NavItemWrapper>
          <LABEL_ICON />
          <Text text="레이블" color="label" fontWeight="bold" />
          <Text text={`(${labelLength})`} color="label" />
        </NavItemWrapper>
      </Link>
    </LinkWrapper>
  );
}

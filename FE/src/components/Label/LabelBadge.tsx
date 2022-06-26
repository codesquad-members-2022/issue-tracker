import styled from 'styled-components';
import { Text } from '@/components/common/Text';

type BadgeWrapperProps = {
  backgroundColor: string;
};

type LabelBadgeProps = {
  name: string;
  textColor?: 'offwhite' | 'titleArchieve';
} & BadgeWrapperProps;

const BadgeWrapper = styled.div.attrs<BadgeWrapperProps>(
  ({ backgroundColor = '#004DE3' }) => ({
    style: {
      backgroundColor: `${backgroundColor}`,
    },
  }),
)<BadgeWrapperProps>`
  padding: 4px 16px;
  border-radius: 30px;
`;

export function LabelBadge({
  name,
  backgroundColor,
  textColor = 'offwhite',
}: LabelBadgeProps) {
  return (
    <BadgeWrapper backgroundColor={backgroundColor}>
      <Text text={name} color={textColor} />
    </BadgeWrapper>
  );
}

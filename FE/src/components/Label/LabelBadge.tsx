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
  border-radius: 30px;
  width: fit-content;
  height: fit-content;
  padding: 4px 16px;
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

import { Styled_logo, ILogoProps } from '@/components/Logo/style';

export default function Logo({ type, title = 'Issue Tracker' }: ILogoProps) {
  return <Styled_logo type={type}>{title}</Styled_logo>;
}

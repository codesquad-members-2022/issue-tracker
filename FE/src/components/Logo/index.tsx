import { Styled_logo, ILogoProps } from '@/components/Logo/style';

export default function Logo({ type }: ILogoProps) {
  return <Styled_logo type={type}>Issue Tracker</Styled_logo>;
}

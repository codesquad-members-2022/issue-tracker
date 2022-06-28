import { $Logo, ILogoProps } from '@/components/common/Logo/style';

export default function Logo({ type, title = 'Issue Tracker' }: ILogoProps) {
  return <$Logo type={type}>{title}</$Logo>;
}

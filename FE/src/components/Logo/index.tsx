import { $Logo, ILogoProps } from '@/components/Logo/style';

export default function Logo({ type, title = 'Issue Tracker' }: ILogoProps) {
  return <$Logo type={type}>{title}</$Logo>;
}

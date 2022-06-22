import Logo from '@/components/Logo';
import UserProfile from '@/components//UserProfile';
import { $Header } from '@/components/Header/style';
import { IUserProfile } from '@/components//UserProfile/type';

export default function Header({ src }: IUserProfile) {
  return (
    <$Header>
      <Logo type="medium" />
      <UserProfile src={src} />
    </$Header>
  );
}

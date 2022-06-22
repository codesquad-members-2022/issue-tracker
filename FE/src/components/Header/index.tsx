import { $Header } from '@/components/Header/style';
import Logo from '../Logo';
import UserProfile from '../UserProfile';
import { IUserProfile } from '../UserProfile/type';

export default function Header({ src }: IUserProfile) {
  return (
    <$Header>
      <Logo type="medium" />
      <UserProfile src={src} />
    </$Header>
  );
}

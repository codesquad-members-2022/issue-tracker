import Logo from '@/components/common/Logo';
import UserProfile from '@/components/common/UserProfile';
import { $Header } from '@/components/Header/style';
import { getCurrentUserInfoOf } from '@/utils/user';
import { useEffect, useState } from 'react';

export default function Header() {
  const [profileURL, setProfileURL] = useState('/');

  useEffect(() => {
    const profileURL = getCurrentUserInfoOf('image_url');
    setProfileURL(profileURL ? (profileURL as string) : '');
  });

  return (
    <$Header>
      <Logo type="medium" />
      <UserProfile src={profileURL} />
    </$Header>
  );
}

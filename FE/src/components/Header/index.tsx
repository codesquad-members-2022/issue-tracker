import Logo from '@/components/Logo';
import UserProfile from '@/components/UserProfile';
import { $Header } from '@/components/Header/style';
import { useEffect, useState } from 'react';

export default function Header() {
  const [profileURL, setProfileURL] = useState('/');

  useEffect(() => {
    const currentUserInfo = localStorage.getItem('currentUserInfo');

    if (!currentUserInfo) return;

    const { image_url } = JSON.parse(currentUserInfo);

    setProfileURL(image_url);
  });

  return (
    <$Header>
      <Logo type="medium" />
      <UserProfile src={profileURL} />
    </$Header>
  );
}

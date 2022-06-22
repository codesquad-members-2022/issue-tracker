import { Icon } from '@/components/Icon';
import { $ProfileImg, $ProfileWrapper } from '@/components/UserProfile/style';
import { IUserProfile } from '@/components/UserProfile/type';

export default function UserProfile({ src }: IUserProfile) {
  return (
    <$ProfileWrapper>
      {!src ? <Icon iconType="github" /> : <$ProfileImg src={src} />}
    </$ProfileWrapper>
  );
}

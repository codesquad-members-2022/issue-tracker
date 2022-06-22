import { $ProfileImg, $ProfileWrapper } from '@/components/UserProfile/style';
import { IUserProfile } from '@/components/UserProfile/type';

export default function UserProfile({ src }: IUserProfile) {
  return (
    <$ProfileWrapper>
      <$ProfileImg src={src} />
    </$ProfileWrapper>
  );
}

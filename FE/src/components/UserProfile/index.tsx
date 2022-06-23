import { $ProfileImg, $ProfileWrapper } from '@/components/UserProfile/style';
import { IUserProfile } from '@/components/UserProfile/type';

export default function UserProfile({ src, size = 'large' }: IUserProfile) {
  return (
    <$ProfileWrapper size={size}>
      <$ProfileImg src={src} />
    </$ProfileWrapper>
  );
}

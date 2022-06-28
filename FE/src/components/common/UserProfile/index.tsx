import { $ProfileImg, $ProfileWrapper } from '@/components/common/UserProfile/style';
import { IUserProfile } from '@/components/common/UserProfile/type';

export default function UserProfile({ src, size = 'large' }: IUserProfile) {
  return (
    <$ProfileWrapper size={size}>
      <$ProfileImg src={src} />
    </$ProfileWrapper>
  );
}

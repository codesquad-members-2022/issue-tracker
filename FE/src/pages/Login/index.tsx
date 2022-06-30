import * as S from './style';
import ISSUETRACKER_IMG from '@/assets/LogoIMG.svg';
import { Logo } from '@/components/common/Logo';
import { LoginSection } from '@/templates/LoginSection';
import LOGO_LARGE from '@/assets/LogoIMGLarge.svg';

export function LoginPage() {
  return (
    <S.LoginLayOut>
      <LOGO_LARGE />
      <LoginSection />
    </S.LoginLayOut>
  );
}

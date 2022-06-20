import { $Button, $TextWrapper } from '@/components/Button/style';
import { IButtonProps } from '@/components/Button/type';
import { Icon } from '@/components/Icon';

export default function Button({ iconType, iconColor, text, children, ...props }: IButtonProps) {
  return (
    <$Button {...props}>
      {iconType && <Icon IconType={iconType} color={iconColor} />}
      <$TextWrapper>{text || children}</$TextWrapper>
    </$Button>
  );
}

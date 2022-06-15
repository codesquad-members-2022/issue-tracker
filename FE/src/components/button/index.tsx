import { Styled_button, Styled_TextWrapper } from '@/components/Button/style';
import { IButtonProps } from '@/components/Button/type';
import { Icon } from '@/components/Icon';

function Button({ iconType, iconColor, text, children, ...props }: IButtonProps) {
  return (
    <Styled_button {...props}>
      {iconType && <Icon IconType={iconType} color={iconColor} />}
      <Styled_TextWrapper>{text || children}</Styled_TextWrapper>
    </Styled_button>
  );
}

export { Button };

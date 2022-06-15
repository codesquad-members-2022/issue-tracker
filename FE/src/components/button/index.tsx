import { Styled_button, Styled_TextWrapper } from '@/components/Button/style';
import { ButtonProps } from '@/components/Button/type';
import { Icon } from '@/components/Icon';

function Button({
  styleType,
  iconType,
  iconColor,
  text,
  children,
  onClick = () => {},
  ...props
}: ButtonProps) {
  return (
    <Styled_button styleType={styleType} onClick={onClick} {...props}>
      {iconType && <Icon IconType={iconType} color={iconColor} />}
      <Styled_TextWrapper>{text || children}</Styled_TextWrapper>
    </Styled_button>
  );
}

export { Button };

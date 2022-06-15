import {
  ButtonStyleType,
  Styled_button,
  Styled_TextWrapper
} from '@/components/Button/Button.style';
import { Icon, IconTypes } from '@/components/Icon';

interface ButtonProps {
  styleType: ButtonStyleType;
  iconType?: IconTypes;
  iconColor?: string;
  text?: string;
  children?: React.ReactNode;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

function Button({
  styleType,
  iconType,
  iconColor,
  text,
  children,
  onClick = () => {}
}: ButtonProps) {
  return (
    <Styled_button styleType={styleType} onClick={onClick}>
      {iconType && <Icon IconType={iconType} color={iconColor} />}
      <Styled_TextWrapper></Styled_TextWrapper>
      {text || children}
    </Styled_button>
  );
}

export { Button };

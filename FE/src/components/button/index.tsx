import { $Button } from '@/components/Button/style';
import { IButtonProps } from '@/components/Button/type';

export default function Button({ children, ...props }: IButtonProps) {
  return <$Button {...props}>{children}</$Button>;
}

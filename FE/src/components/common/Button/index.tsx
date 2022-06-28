import { $Button } from '@/components/common/Button/style';
import { IButtonProps } from '@/components/common/Button/type';

export default function Button({ children, ...props }: IButtonProps) {
  return <$Button {...props}>{children}</$Button>;
}

import { $Label } from '@/components/common/Label/style';
import { ILabelProps } from '@/components/common/Label/type';

export default function Label({ children, size = 'large', status, background }: ILabelProps) {
  return (
    <$Label size={size} status={status} background={background}>
      {children}
    </$Label>
  );
}

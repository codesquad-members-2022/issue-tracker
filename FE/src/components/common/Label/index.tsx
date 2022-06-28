import { $Label } from '@/components/common/Label/style';
import { ILabelProps } from '@/components/common/Label/type';

export default function Label({ children, size = 'large', status }: ILabelProps) {
  return (
    <$Label size={size} status={status}>
      {children}
    </$Label>
  );
}

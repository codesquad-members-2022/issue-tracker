import { $Label } from '@/components/Label/style';
import { ILabelProps } from '@/components/Label/type';

export default function Label({ children, size = 'large', status }: ILabelProps) {
  return (
    <$Label size={size} status={status}>
      {children}
    </$Label>
  );
}

import { $Label } from '@/components/Label/style';
import { ILabelProps } from '@/components/Label/type';

export default function Label({ children, status }: ILabelProps) {
  return <$Label status={status}>{children}</$Label>;
}

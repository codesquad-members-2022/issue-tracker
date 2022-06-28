import { Option } from '@/components/common/Dropdown/Panel/type';
import { StyleType } from '@/components/common/TextInput/type';

interface IFilterBarProps {
  indicatorName: string;
  panelName: string;
  options: Option[];
  initialValue?: string;
  inputStyleType?: StyleType;
  placeholder: string;
  label?: string;
  name: string;
  defaultValue?: string;
}

export type { IFilterBarProps };

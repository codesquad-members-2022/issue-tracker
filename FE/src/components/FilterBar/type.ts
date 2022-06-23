import { Option } from '@/components/Dropdown/Panel/type';
import { StyleType } from '@/components/TextInput/type';

interface IFilterBarProps {
  indicatorName: string;
  panelName: string;
  options: Option[];
  initialValue: string;
  inputStyleType?: StyleType;
  placeholder: string;
  label?: string;
  name: string;
  defaultValue?: string;
}

export type { IFilterBarProps };

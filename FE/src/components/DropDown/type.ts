import { Option, I$PanelProps } from '@/components/Dropdown/Panel/type';

interface IDropDown extends I$PanelProps {
  indicatorName: string;
  panelName: string;
  options: Option[];
  initialValue: string | undefined;
}

export type { IDropDown };

import { Option, I$PanelProps } from '@/components/Dropdown/Panel/type';

interface IDropDown extends I$PanelProps {
  indicatorName: string;
  indicatorGap?: string;
  indicatorPadding?: string;
  panelName: string;
  options: Option[];
  initialValue?: string;
}

export type { IDropDown };

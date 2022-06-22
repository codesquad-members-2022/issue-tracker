import React from 'react';

type Left = 'left' | 'right';

type Option = {
  children: React.ReactNode | string;
  radio?: { on: React.ReactNode; off: React.ReactNode };
  value: string;
};

interface I$PanelProps {
  top?: string;
  left?: Left;
  width?: string;
}

interface I$SelectedItemProps {
  selected: boolean;
}

interface IPanelProps extends I$PanelProps {
  panelRef: React.MutableRefObject<HTMLDivElement | null>;
  title: string;
  options: Option[];
  selected?: string;
  handlePanelBlur?: () => void;
}

export type { I$PanelProps, I$SelectedItemProps, IPanelProps };

import React from 'react';

type Size = 'large' | 'small';

type Status = 'open' | 'close' | 'dark' | 'light' | 'line';

interface ILabelProps {
  children: React.ReactNode | string;
  size?: Size;
  status: Status;
}

export type { Size, Status, ILabelProps };

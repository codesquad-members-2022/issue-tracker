import React from 'react';

type Status = 'open' | 'close';

interface ILabelProps {
  children: React.ReactNode | string;
  status: Status;
}

export type { Status, ILabelProps };

import { Styled_button } from './button.style';
import React, { useContext } from 'react';

interface ButtonProps {
  width: string;
  children: React.ReactNode;
}

export default function Button({ children, width = '50px' }: ButtonProps) {
  return <Styled_button width={width}>{children}</Styled_button>;
}

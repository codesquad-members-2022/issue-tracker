import React from 'react';
import { Styled_textInput } from '@/components/TextInput/style';
import { StyleType } from '@/components/TextInput/type';

interface TextInputProps {
  styleType?: StyleType;
  width?: string;
  height?: string;
  color?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
  placeholder?: string;
  label?: string;
}

export default function TextInput({ placeholder = '', label, ...props }: TextInputProps) {
  return <Styled_textInput type="text" placeholder={placeholder} {...props} />;
}

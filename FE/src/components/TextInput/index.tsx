import { useState } from 'react';
import { Styled_textInputWrap, Styled_label, Styled_textInput } from '@/components/TextInput/style';
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
  maxLength?: number;
}

const MIN_INPUT_VALUE_LENGTH = 1;

export default function TextInput({
  placeholder = '',
  label,
  maxLength,
  ...props
}: TextInputProps) {
  const [visibleLabel, setVisibleLabel] = useState(false);

  const showLabel = (event: React.ChangeEvent<HTMLInputElement>) => {
    const hasValue = event.target.value.length >= MIN_INPUT_VALUE_LENGTH;
    setVisibleLabel(hasValue);
  };

  return (
    <Styled_textInputWrap>
      {label && (
        <Styled_label styleType={props.styleType} visible={visibleLabel}>
          {label}
        </Styled_label>
      )}
      <Styled_textInput
        {...props}
        type="text"
        placeholder={placeholder}
        maxLength={maxLength}
        visibleLabel={visibleLabel}
        onChange={event => label && showLabel(event)}
      ></Styled_textInput>
    </Styled_textInputWrap>
  );
}

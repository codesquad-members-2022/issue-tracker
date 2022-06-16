import { useState } from 'react';
import { Styled_textInputWrap, Styled_label, Styled_textInput } from '@/components/TextInput/style';
import { TextInputProps } from '@/components/TextInput/type';

const MIN_INPUT_VALUE_LENGTH = 1;

export default function TextInput({
  placeholder = '',
  label,
  maxLength,
  as,
  ...props
}: TextInputProps) {
  const [visibleLabel, setVisibleLabel] = useState(false);
  const Input = as || Styled_textInput;

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
      <Input
        {...props}
        type="text"
        placeholder={placeholder}
        maxLength={maxLength}
        visibleLabel={visibleLabel}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => label && showLabel(event)}
      ></Input>
    </Styled_textInputWrap>
  );
}

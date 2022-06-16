import { useState } from 'react';
import { Styled_textInputWrap, Styled_textInput, Styled_label } from '@/components/TextInput/style';
import { ITextInputProps } from '@/components/TextInput/type';

const MIN_INPUT_VALUE_LENGTH = 1;

export default function TextInput({
  placeholder = '',
  label,
  maxLength,
  type = 'text',
  as,
  ...props
}: ITextInputProps) {
  const [visibleLabel, setVisibleLabel] = useState(false);
  const TextInputWrap = as || Styled_textInputWrap;

  const showLabel = (event: React.ChangeEvent<HTMLInputElement>) => {
    const hasValue = event.target.value.length >= MIN_INPUT_VALUE_LENGTH;
    setVisibleLabel(hasValue);
  };

  return (
    <TextInputWrap>
      {label && (
        <Styled_label styleType={props.styleType} visible={visibleLabel}>
          {label}
        </Styled_label>
      )}
      <Styled_textInput
        {...props}
        type={type}
        placeholder={placeholder}
        maxLength={maxLength}
        visibleLabel={visibleLabel}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => label && showLabel(event)}
      ></Styled_textInput>
    </TextInputWrap>
  );
}

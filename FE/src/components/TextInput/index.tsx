import { useState } from 'react';
import { $TextInputWrap, $TextInput, $Label } from '@/components/TextInput/style';
import { ITextInputProps } from '@/components/TextInput/type';

const MIN_INPUT_VALUE_LENGTH = 1;

export default function TextInput({
  placeholder = '',
  label,
  type = 'text',
  name,
  as,
  handleChange,
  ...props
}: ITextInputProps) {
  const [visibleLabel, setVisibleLabel] = useState(false);
  const TextInputWrap = as || $TextInputWrap;

  const showLabel = (event: React.ChangeEvent<HTMLInputElement>) => {
    const hasValue = event.target.value.length >= MIN_INPUT_VALUE_LENGTH;
    setVisibleLabel(hasValue);
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (handleChange) handleChange(event);
    showLabel(event);
  };

  return (
    <TextInputWrap>
      {label && (
        <$Label styleType={props.styleType} visible={visibleLabel}>
          {label}
        </$Label>
      )}
      <$TextInput
        {...props}
        type={type}
        name={name}
        placeholder={placeholder}
        visibleLabel={visibleLabel}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => handleInputChange(event)}
      />
    </TextInputWrap>
  );
}

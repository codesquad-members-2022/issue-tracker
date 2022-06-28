import { useState } from 'react';
import { $TextInputWrap, $TextInput, $Label } from '@/components/common/TextInput/style';
import { ITextInputProps } from '@/components/common/TextInput/type';

const MIN_INPUT_VALUE_LENGTH = 1;

export default function TextInput<T extends string>({
  placeholder = '',
  label,
  type = 'text',
  name,
  handleChange,
  ...props
}: ITextInputProps<T>) {
  const [visibleLabel, setVisibleLabel] = useState(false);

  const showLabel = (target: HTMLInputElement) => {
    const hasValue = target.value.length >= MIN_INPUT_VALUE_LENGTH;
    setVisibleLabel(hasValue);
  };

  const handleInputChange = (target: HTMLInputElement) => {
    if (handleChange) handleChange(target);
    if (label) showLabel(target);
  };

  return (
    <$TextInputWrap>
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
        onChange={({ target }: { target: HTMLInputElement }) => handleInputChange(target)}
      />
    </$TextInputWrap>
  );
}

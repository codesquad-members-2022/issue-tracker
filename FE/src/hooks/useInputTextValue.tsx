import { useState } from 'react';
import debounce from '@/utils/debounce';

export default function useInputTextValue<T>(value: string) {
  const [inputInfo, setInputValue] = useState<{ name: T | null; value: string }>({
    name: null,
    value
  });

  const updateInputValue = (name: T, value: string, ms?: number) => {
    debounce(
      () =>
        setInputValue({
          name: name,
          value: value
        }),
      ms ? ms : 0
    );
  };

  const checkInputValueLength = (min: number, max?: number) =>
    min <= inputInfo.value.length && (max ? max : Infinity) >= inputInfo.value.length;

  return { inputInfo, setInputValue, updateInputValue, checkInputValueLength };
}

import { useState } from 'react';
import debounce from '@/utils/debounce';

export default function useInputTextValue(value: string) {
  const [inputInfo, setInputValue] = useState<{ name: string | null; value: string }>({
    name: null,
    value
  });

  const updateInputValue = (event: React.ChangeEvent<HTMLInputElement>, ms?: number) => {
    const target = event.target;
    debounce(
      () =>
        setInputValue({
          name: target.name,
          value: target.value
        }),
      ms ? ms : 0
    );
  };

  const checkInputValueLength = (min: number, max?: number) =>
    min <= inputInfo.value.length && (max ? max : Infinity) >= inputInfo.value.length;

  return { inputInfo, setInputValue, updateInputValue, checkInputValueLength };
}

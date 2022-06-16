import { useState } from 'react';
import debounce from '@/utils/debounce';

export default function useInputTextValue() {
  const [inputValue, setInputValue] = useState('');

  const updateInputValue = (event: React.ChangeEvent<HTMLInputElement>, ms?: number) => {
    debounce(() => setInputValue(event.target.value), ms ? ms : 0);
  };

  const checkInputValueLength = (min?: number, max?: number) =>
    (min ? min : 0) <= inputValue.length && (max ? max : Infinity) >= inputValue.length;

  return { inputValue, setInputValue, updateInputValue, checkInputValueLength };
}

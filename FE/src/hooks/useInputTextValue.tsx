import { useState } from 'react';
import debounce from '@/utils/debounce';

export default function useInputTextValue() {
  const [inputValue, setInputValue] = useState('');

  const updateInputValue = (event: React.ChangeEvent<HTMLInputElement>, ms: number) => {
    debounce(() => setInputValue(event.target.value), ms);
  };

  const checkInputValueLength = (min: number, max: number) =>
    min <= inputValue.length && max >= inputValue.length;

  return { inputValue, setInputValue, updateInputValue, checkInputValueLength };
}

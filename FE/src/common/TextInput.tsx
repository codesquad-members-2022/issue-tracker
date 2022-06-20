import styled from 'styled-components';
import { GREYSCALE } from '@/constants';

type TextInputProps = {
  type: 'text' | 'password';
  placeholder: string;
  value: string;
  minLength: number;
  maxLength: number;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  ref?: React.MutableRefObject<null | HTMLInputElement>;
};

function TextInput({
  type,
  placeholder,
  value,
  minLength,
  maxLength,
  onChange,
  ref,
}: TextInputProps) {
  return (
    <TextInputBox>
      <InputBox
        type={type}
        placeholder={placeholder}
        value={value}
        minLength={minLength}
        maxLength={maxLength}
        onChange={onChange}
        ref={ref}
      />
    </TextInputBox>
  );
}

const TextInputBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column')}
`;

const InputBox = styled.input`
  width: 340px;
  height: 64px;
  background-color: ${GREYSCALE.INPUT_BACKGROUND};
  border-radius: 16px;
  padding: 18px 24px;
  color: ${GREYSCALE.TITLE_ACTION};
  border: 1px solid ${GREYSCALE.INPUT_BACKGROUND};
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_SMALL}

  ::placeholder {
    color: ${GREYSCALE.PLACEHOLDER};
  }

  :focus {
    background-color: ${GREYSCALE.OFF_WHITE};
    border: 1px solid ${GREYSCALE.TITLE_ACTION};
  }
`;

export default TextInput;

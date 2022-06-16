import { GREYSCALE, COLORS } from '@/constants';
import React from 'react';
import styled from 'styled-components';

function TextInput({ type, placeholder, isSuccess }) {
  const Caption = isSuccess ? SuccessCaption : ErrorCaption;
  const captionText = isSuccess
    ? '사용 가능한 아이디 입니다!'
    : '이미 사용하고 있는 아이디 입니다!';

  return (
    <TextInputBox>
      <InputBox type={type} placeholder={placeholder}></InputBox>
      {/* <Caption>{captionText}</Caption> */}
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

const CaptionTag = styled.div`
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_X_SMALL}
`;

const SuccessCaption = styled(CaptionTag)`
  color: ${COLORS.DARK_GREEN};
`;

const ErrorCaption = styled(CaptionTag)`
  color: ${COLORS.DARK_RED};
`;

export default TextInput;

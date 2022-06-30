import React, { ReactNode, useState } from 'react';
import axios from 'axios';
import { Form, StyledTextarea, AddFile, Count } from 'components/Atoms/Textarea/index.styles';
import Icon from 'components/Atoms/Icon/';

export interface TextareaTypes {
  textareaSize: 'MEDIUM' | 'LARGE';
  textareaPlaceholder?: string;
  textareaMaxLength?: number;
  textareaValue?: string;
  textareaRef: React.RefObject<HTMLTextAreaElement>;
  children?: ReactNode;
}

const defaultTextareaMaxLength = 1000;
const Textarea = ({
  textareaSize = 'LARGE',
  textareaMaxLength = defaultTextareaMaxLength,
  ...props
}: TextareaTypes) => {
  const { textareaPlaceholder, textareaValue, textareaRef } = props;

  const [typingValue, setTypingValue] = useState<string>(textareaValue || '');
  const [isActive, setIsActive] = useState<boolean>(false);

  const maxLength = 120;

  const handleFormClick = () => {
    textareaRef.current?.focus();
    setIsActive(true);
  };

  const handleTextareaChange = (event: React.FormEvent<HTMLTextAreaElement>) => {
    const { value } = event.currentTarget;
    if (!value) return setTypingValue('');
    // eslint-disable-next-line no-param-reassign
    if (Number(value) >= maxLength) event.currentTarget.value = value.slice(0, maxLength);
    return setTypingValue(value);
  };

  const count = typingValue.length || 0;

  const handleUpload = async (e: { target: HTMLInputElement }) => {
    const file = e.target.files![0];

    const { data } = await axios.post(
      `${process.env.REACT_APP_SERVER_URL}/api/upload`,
      { file },
      {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      },
    );

    textareaRef.current!.value += `\n![](${data.fileUrl})\n`;
  };

  return (
    <Form isActive={isActive} onClick={handleFormClick}>
      {typingValue && (
        <label className="textarea" htmlFor="textarea">
          <span>{textareaPlaceholder}</span>
        </label>
      )}
      <StyledTextarea
        id="textarea"
        defaultValue={textareaValue}
        maxLength={textareaMaxLength}
        placeholder={textareaPlaceholder}
        textareaSize={textareaSize}
        ref={textareaRef}
        onBlur={() => setIsActive(false)}
        onChange={handleTextareaChange}
      />
      <Count>{`띄어쓰기 포함 ${count}자`}</Count>
      <AddFile>
        <label className="addFile" htmlFor="addFile">
          <input id="addFile" type="file" onChange={handleUpload} />
          <Icon icon="PaperClip" />
          <span>파일 첨부하기</span>
        </label>
      </AddFile>
    </Form>
  );
};

export default Textarea;

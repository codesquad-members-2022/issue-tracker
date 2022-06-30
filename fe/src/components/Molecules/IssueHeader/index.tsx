import { useRef, useState } from 'react';

import { colors } from 'styles/theme';
import Icon from 'components/Atoms/Icon/index';
import Label from 'components/Atoms/Label';
import Button from 'components/Atoms/Button';
import Input from 'components/Atoms/Input';
import { StyledIssueTitle, StyledIssueInfo } from 'components/Molecules/IssueHeader/index.styles';

import { AssignTypes } from 'components/types';

import useInput from 'hooks/useInput';
import convertPreviousDate from 'helpers/utils/convertPreviousDate';

interface IssueHeaderTypes {
  id: number;
  title?: string;
  status: 'OPEN' | 'CLOSED';
  isEditer: boolean;
  writer: AssignTypes;
  createTime: string;
  commentCount: number;
}

const IssueHeader = ({ status, isEditer, writer, id, title, createTime, commentCount }: IssueHeaderTypes) => {
  const [isEditable, setIstEditable] = useState<boolean>(false);
  const { isActive, onChangeInput, onClickInput, onBlurInput } = useInput();

  const labelTitle = status === 'OPEN' ? '열린 이슈' : '닫힌 이슈';
  const labelBackgroundColor = status === 'OPEN' ? colors.primary.lightBlue : colors.secondary.lightPurple;
  const labelTextColor = status === 'OPEN' ? colors.primary.blue : colors.secondary.purple;

  const inputRef = useRef<HTMLInputElement>(null);

  const handleEditButtonClick = () => {
    setIstEditable((editable) => !editable);
  };

  const [content, setContent] = useState(title);
  const handleChangeComment = () => {
    setContent(inputRef.current?.value);
    setIstEditable((editable) => !editable);
  };

  const timeStamp = convertPreviousDate(new Date(), createTime);

  return (
    <>
      <StyledIssueTitle>
        <div className="issueTitle">
          {isEditable ? (
            <Input
              inputMaxLength={30}
              inputPlaceholder="제목"
              inputSize="SMALL"
              inputStyle="STANDARD"
              inputType="text"
              inputValue={content}
              inputRef={inputRef}
              isTyping
              isActive={isActive}
              onBlur={onBlurInput}
              onChange={onChangeInput}
              onClick={onClickInput}
            />
          ) : (
            <>
              <h1>{content}</h1>
              <h2 className="issueNumber">{`#${id}`}</h2>
            </>
          )}
        </div>
        {isEditer && (
          <div className="issueButtons">
            {isEditable ? (
              <>
                <Button
                  buttonStyle="SECONDARY"
                  iconInfo={{ icon: 'XSquare' }}
                  label="편집 취소"
                  size="SMALL"
                  HandleOnClick={handleEditButtonClick}
                />
                <Button
                  buttonStyle="STANDARD"
                  iconInfo={{ icon: 'Edit', stroke: '#FEFEFE' }}
                  label="편집 완료"
                  size="SMALL"
                  HandleOnClick={handleChangeComment}
                />
              </>
            ) : (
              <>
                <Button
                  buttonStyle="SECONDARY"
                  iconInfo={{ icon: 'Edit' }}
                  label="제목 편집"
                  size="SMALL"
                  HandleOnClick={handleEditButtonClick}
                />
                <Button buttonStyle="SECONDARY" iconInfo={{ icon: 'Archive' }} label="이슈 닫기" size="SMALL" />
              </>
            )}
          </div>
        )}
      </StyledIssueTitle>
      <StyledIssueInfo status={status}>
        <Label
          backgroundColor={labelBackgroundColor}
          description="string"
          id={0}
          labelStyle="STANDARD"
          title={labelTitle}
          titleColor="black"
          icon={<Icon fill={labelBackgroundColor} icon="AlertCircle" stroke={labelTextColor} />}
        />
        <span>{`이 이슈가 ${timeStamp}에 ${writer.loginId}님에 의해 열렸습니다`}</span>
        <span className="splitLine">∙</span>
        <span>{`코멘트 ${commentCount}개`}</span>
      </StyledIssueInfo>
    </>
  );
};

export default IssueHeader;

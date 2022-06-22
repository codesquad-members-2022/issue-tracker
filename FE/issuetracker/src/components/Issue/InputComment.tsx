import { useState } from 'react';
import { useRecoilState } from 'recoil';

import * as I from 'design/icons';
import * as S from 'components/Issue/styled/inputComment';

import userImageURL from 'assets/images/UserImageLarge.svg';
import { issueState } from 'context/issue';
import { calculateInterval } from 'utils/util';

function InputComment() {
  const [issueData, setIssueData] = useRecoilState(issueState);
  const [isInputActive, setIsInputActive] = useState(false);
  const [inputValue, setInputValue] = useState('');

  const focusInput = () => {
    setIsInputActive(true);
  };

  const blurInput = () => {
    setIsInputActive(false);
  };

  const changeInput = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setInputValue(e.target.value);
  };

  const saveInput = () => {
    const newComment = {
      writer: issueData.writer,
      log: `${calculateInterval(issueData.writtenTime)}초 전`,
      description: inputValue,
    };
    const updatedIssueData = {
      ...issueData,
      comments: [...issueData.comments, newComment],
    };
    setIssueData(updatedIssueData);
    setInputValue('');
  };

  return (
    <S.inputComment>
      <S.commentWrapper>
        <S.commentUserImage>
          <img src={userImageURL} alt="user-icon-img" />
        </S.commentUserImage>
        <S.comment isInputActive={isInputActive} onFocus={focusInput} onBlur={blurInput}>
          <S.textArea placeholder="코멘트를 입력하세요" value={inputValue} onChange={changeInput} />
          <S.attachFileButtonWrapper>
            <S.attachFileButton>
              <I.paperclip />
              <S.attachFileButtonText>파일 첨부하기</S.attachFileButtonText>
            </S.attachFileButton>
          </S.attachFileButtonWrapper>
        </S.comment>
      </S.commentWrapper>
      <S.saveCommentButtonWrapper>
        <S.saveCommentButton onClick={saveInput}>
          <I.plus />
          <S.saveCommentButtonText>코멘트 작성</S.saveCommentButtonText>
        </S.saveCommentButton>
      </S.saveCommentButtonWrapper>
    </S.inputComment>
  );
}
export default InputComment;

/* eslint-disable jsx-a11y/label-has-associated-control */
import { useRef, useState } from 'react';

import { colors } from 'styles/theme';
import Icon from 'components/Atoms/Icon/index';
import Label from 'components/Atoms/Label';
import Textarea from 'components/Atoms/Textarea';
import Button from 'components/Atoms/Button';
import UserImage from 'components/Atoms/UserImage';

import { AssignTypes } from 'components/types';

import convertPreviousDate from 'helpers/utils/convertPreviousDate';

import {
  StyledComment,
  StyledCommentList,
  EditButtons,
  CommentHeader,
  CommentContent,
} from 'components/Molecules/Comment/index.styles';

import ReactMarkdown from 'react-markdown';
import remarkGfm from 'remark-gfm';

interface CommentListTypes {
  writer: AssignTypes;
  createdAt: string;
  updatedAt: string;
  content?: string;
  reactions?: object[];
}

export interface CommentTypes {
  isNewComment?: boolean;
  isWriter?: boolean;
  isOpen: boolean;
  comments?: CommentListTypes;
  isIssueWriter?: boolean;
  isEditer?: boolean;
}

const Comment = ({ isWriter = false, isOpen, isNewComment, comments, isIssueWriter, isEditer }: CommentTypes) => {
  // 다시 열릴 경우
  const openComment = '이슈가 열렸습니다.';
  const closeComment = '이슈가 닫혔습니다.';

  const [isEditable, setIstEditable] = useState<boolean>(false);

  const handleEditButtonClick = () => {
    setIstEditable((editable) => !editable);
  };

  const [content, setContent] = useState(comments?.content);

  const textareaRef = useRef<HTMLTextAreaElement>(null);

  const handleChangeComment = () => {
    setContent(textareaRef.current?.value);
    setIstEditable((editable) => !editable);
  };

  const userInfoData = window.localStorage.getItem('userInfo');
  const userInfo = JSON.parse(userInfoData!);

  return (
    <StyledCommentList>
      <UserImage
        imgSize="MEDIUM"
        loginId={isNewComment ? userInfo?.loginId : comments?.writer.loginId}
        profileImageUrl={isNewComment ? userInfo?.profileImageUrl : comments?.writer.profileImageUrl}
      />
      <StyledComment isOpen={isOpen} isEditable={isEditable} isNewComment={isNewComment}>
        {isEditable || isNewComment ? (
          <>
            <Textarea
              textareaPlaceholder="코멘트를 입력하세요"
              textareaSize="LARGE"
              textareaValue={content}
              textareaRef={textareaRef}
            />
            <EditButtons>
              {isNewComment ? (
                <Button
                  buttonStyle="STANDARD"
                  iconInfo={{ icon: 'Plus', stroke: `${colors.offWhite}` }}
                  label="코멘트 작성"
                  size="SMALL"
                />
              ) : (
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
                    iconInfo={{ icon: 'Edit', stroke: `${colors.offWhite}` }}
                    label="편집 완료"
                    size="SMALL"
                    HandleOnClick={handleChangeComment}
                  />
                </>
              )}
            </EditButtons>
          </>
        ) : (
          <>
            <div className="table-row">
              <CommentHeader isOpen={isOpen}>
                <div className="commentInfo">
                  <span className="userName">{comments?.writer.loginId}</span>
                  <span className="timeStamp">{convertPreviousDate(new Date(), comments!.updatedAt)}</span>
                </div>
                <div className="commentBadge">
                  {isIssueWriter && (
                    <Label backgroundColor={colors.background} titleColor="black" labelStyle="LINE" title="작성자" />
                  )}
                  {isEditer && (
                    <Button
                      buttonStyle="NO_BORDER"
                      iconInfo={{ icon: 'Edit', stroke: colors.label }}
                      label="편집"
                      size="SMALL"
                      HandleOnClick={handleEditButtonClick}
                    />
                  )}
                  <Icon icon="Smile" stroke={colors.label} />
                </div>
              </CommentHeader>
            </div>
            <div className="table-row">
              <CommentContent>
                {isOpen ? <ReactMarkdown remarkPlugins={[remarkGfm]}>{content!}</ReactMarkdown> : closeComment}
              </CommentContent>
            </div>
          </>
        )}
      </StyledComment>
    </StyledCommentList>
  );
};

export default Comment;

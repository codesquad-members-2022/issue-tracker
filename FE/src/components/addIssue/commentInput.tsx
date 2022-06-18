import React, { useRef, useState, useEffect } from 'react';
import styles from './commentInput.module.scss';

export const CommentInput = (): JSX.Element => {
  const commentRef = useRef<HTMLTextAreaElement | null>(null);
  const [isFocus, setIsFocus] = useState<boolean>(false);

  useEffect(() => {
    commentRef.current?.focus();
  }, [isFocus]);

  const focusHandler = (): void => {
    setIsFocus(true);
  };

  const blurHandler = (): void => {
    setIsFocus(false);
  };

  return (
    <>
      <div
        className={
          isFocus ? `${styles.commentBox} ${styles.onFocus}` : styles.commentBox
        }>
        {isFocus ? (
          <label>
            <h3 className={styles.label}>코멘트를 입력해주세요</h3>
            <textarea
              ref={commentRef}
              placeholder="코멘트를 입력해주세요"
              onBlur={blurHandler}
            />
          </label>
        ) : (
          <textarea
            placeholder="코멘트를 입력해주세요"
            onFocus={focusHandler}
          />
        )}
      </div>
      <div
        className={
          isFocus ? `${styles.fileBox} ${styles.fileBoxFocus}` : styles.fileBox
        }>
        <label className={styles.fileBoxLabel}>
          <img src="assets/img/clip.png" />
          <h3>파일 첨부하기</h3>
          <input type="file" />
        </label>
      </div>
    </>
  );
};

import React, { useEffect, useRef, useState } from 'react';
import styles from './titleInput.module.scss';

export const TitleInput = (): JSX.Element => {
  const titleRef = useRef<HTMLInputElement | null>(null);
  const [isFocus, setIsFocus] = useState<boolean>(false);

  useEffect(() => {
    titleRef.current?.focus();
  }, [isFocus]);

  const focusHandler = (): void => {
    setIsFocus(true);
  };

  const blurHandler = (): void => {
    setIsFocus(false);
  };

  return (
    <div className={isFocus ? `${styles.Box} ${styles.onFocus}` : styles.Box}>
      {isFocus ? (
        <label>
          <h3 className={styles.label}>제목</h3>
          <input
            ref={titleRef}
            type="text"
            placeholder="제목"
            onBlur={blurHandler}
          />
        </label>
      ) : (
        <input type="text" placeholder="제목" onFocus={focusHandler} />
      )}
    </div>
  );
};

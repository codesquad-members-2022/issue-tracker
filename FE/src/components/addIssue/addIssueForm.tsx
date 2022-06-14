import React from 'react';

const AddIssueForm = (): JSX.Element => {
  return (
    <form>
      <div>
        <div></div>
        <div>
          <div>
            <input type="text" placeholder="제목" />
          </div>
          <div>
            <input type="text" placeholder="코멘트를 입력하세요" />
          </div>
          <div>
            <label>
              파일 첨부하기
              <input type="file" />
            </label>
          </div>
        </div>
        <div></div>
      </div>
      <div>
        <button>작성 취소</button>
        <button>완료</button>
      </div>
    </form>
  );
};

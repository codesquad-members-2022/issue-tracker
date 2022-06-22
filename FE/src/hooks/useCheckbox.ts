import React, { useState, useEffect } from 'react';

// data는 체크박스 리스트가 필요한 데이터들의 집합

export const useCheckbox = (checkboxesData) => {
  const [checkedIssues, setCheckedIssues] = useState<string[]>([]);
  const [allBoxIsChecked, setAllBoxIsChecked] = useState(false);

  const allCheckboxHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const isChecked = e.target.checked;
    if (isChecked) {
      const allCheckboxes = checkboxesData.content.map((checkbox) =>
        String(checkbox.issueNumber),
      );
      setCheckedIssues(allCheckboxes);
    } else {
      setCheckedIssues([]);
    }
  };

  const checkboxHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const currentBoxId = e.target.id;

    const alreadyChecked = checkedIssues.includes(currentBoxId);
    if (alreadyChecked) {
      const newState = checkedIssues.filter((issue) => issue !== currentBoxId);
      setCheckedIssues(newState);
    } else {
      setCheckedIssues((prev) => [...prev, currentBoxId]);
    }
  };

  useEffect(() => {
    if (checkedIssues.length) setAllBoxIsChecked(true);
    else setAllBoxIsChecked(false);
  }, [checkedIssues]);

  return {
    checkedIssues,
    allBoxIsChecked,
    allCheckboxHandler,
    checkboxHandler,
  };
};

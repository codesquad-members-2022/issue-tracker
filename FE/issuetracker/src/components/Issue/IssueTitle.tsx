import { useRecoilState } from 'recoil';
import { useState } from 'react';

import * as S from 'components/Issue/styled/issueTitle';

import CancleAndSaveButtons from 'components/Issue/buttons/CancleAndSaveButtons';
import EditAndCloseButtons from 'components/Issue/buttons/EditAndCloseButtons';
import IssueTitleContent from 'components/Issue/IssueTitleContent';
import IssueTitleInput from 'components/Issue/IssueTitleInput';
import { issueState } from 'recoil/atoms/issue';

function IssueTitle() {
  const [issueData, setIssueData] = useRecoilState(issueState);
  const [isEditable, setEditable] = useState(false);
  const [inputValue, setInputValue] = useState('');

  const startEdit = () => {
    setEditable(true);
  };

  const cancleEdit = () => {
    setInputValue('');
    setEditable(false);
  };

  const saveInputValue = () => {
    const updatedIssueData = {
      ...issueData,
      title: inputValue,
    };
    setIssueData(updatedIssueData);
    setInputValue('');
    setEditable(false);
  };

  const toggleIssueState = () => {
    const updatedIssueData = {
      ...issueData,
      closed: !issueData.closed,
    };
    setIssueData(updatedIssueData);
  };

  return (
    <S.issueTitleWrapper>
      {isEditable ? (
        <IssueTitleInput inputValue={inputValue} setInputValue={setInputValue} />
      ) : (
        <IssueTitleContent />
      )}
      {isEditable ? (
        <CancleAndSaveButtons clickHandler1={cancleEdit} clickHandler2={saveInputValue} />
      ) : (
        <EditAndCloseButtons clickHandler1={startEdit} clickHandler2={toggleIssueState} />
      )}
    </S.issueTitleWrapper>
  );
}
export default IssueTitle;

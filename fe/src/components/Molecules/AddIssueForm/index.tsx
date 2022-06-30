import UserImage from 'components/Atoms/UserImage';
import Input, { InputTypes } from 'components/Atoms/Input';
import Textarea, { TextareaTypes } from 'components/Atoms/Textarea';
import { StyledAddIssueForm, IssueForm } from 'components/Molecules/AddIssueForm/index.styles';

export type AddIssueFormTypes = InputTypes & TextareaTypes;

const AddIssueForm = (props: AddIssueFormTypes) => {
  const {
    isActive,
    isTyping,
    inputSize,
    inputType,
    inputMaxLength,
    inputPlaceholder,
    inputRef,
    onChange,
    onClick,
    onBlur,
    textareaSize,
    textareaMaxLength,
    textareaPlaceholder,
    textareaRef,
  } = props;

  const userData = localStorage.getItem('userInfo');
  const { loginId, profileImageUrl } = JSON.parse(userData!);

  return (
    <StyledAddIssueForm>
      <UserImage imgSize="MEDIUM" loginId={loginId} profileImageUrl={profileImageUrl} />
      <IssueForm>
        <Input
          isActive={isActive}
          isTyping={isTyping}
          onChange={onChange}
          onClick={onClick}
          onBlur={onBlur}
          inputSize={inputSize}
          inputType={inputType}
          inputMaxLength={inputMaxLength}
          inputPlaceholder={inputPlaceholder}
          inputRef={inputRef}
        />
        <Textarea
          textareaPlaceholder={textareaPlaceholder}
          textareaMaxLength={textareaMaxLength}
          textareaSize={textareaSize}
          textareaRef={textareaRef}
        />
      </IssueForm>
    </StyledAddIssueForm>
  );
};

export default AddIssueForm;

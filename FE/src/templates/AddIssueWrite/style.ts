import styled from 'styled-components';

export const AddIssueWrite = styled.div`
  display: flex;
  gap: 16px;
`;

export const IssueType = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
  width: 880px;
`;

export const IssueTypeTitle = styled.input`
  width: 100%;
  padding: 14px 24px;
  background-color: ${({ theme }) => theme.color.inputBackground};
  border: 1px solid ${({ theme }) => theme.color.line};
  border-radius: 16px;
  box-sizing: border-box;
  color: ${({ theme }) => theme.color.titleArchieve};

  ::placeholder {
    color: ${({ theme }) => theme.color.placeholder};
  }
  :focus {
    outline: none;
  }
`;

export const TitleLabel = styled.label`
  color: ${({ theme }) => theme.color.label};
  font-size: ${({ theme }) => theme.fontSize.smaller};
`;

export const IssueTypeContents = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 240px;
  padding: 16px 0;
  background-color: ${({ theme }) => theme.color.inputBackground};
  border: 1px solid ${({ theme }) => theme.color.line};
  border-radius: 16px;
  box-sizing: border-box;
  color: ${({ theme }) => theme.color.titleArchieve};
`;

export const IssueTextArea = styled.textarea`
  box-sizing: border-box;
  padding: 0 16px;
  height: 300px;
  min-height: 200px;
  background-color: ${({ theme }) => theme.color.inputBackground};
  border: none;

  color: ${({ theme }) => theme.color.titleArchieve};

  ::placeholder {
    color: ${({ theme }) => theme.color.placeholder};
  }

  :focus {
    outline: none;
  }
  resize: vertical;
`;

export const AttachFile = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  border-top: 1px dashed ${({ theme }) => theme.color.line};
  padding: 16px 16px 0 16px;
  margin-top: 10px;
  box-sizing: border-box;

  color: ${({ theme }) => theme.color.label};
  font-size: ${({ theme }) => theme.fontSize.smaller};
  font-weight: ${({ theme }) => theme.fontWeight.bold};
`;

export const AttachFileLabel = styled.label`
  width: 16px;
  height: 16px;
  padding: 8px;
  display: inline-block;
  cursor: pointer;
  input[type='file'] {
    display: none;
  }
`;

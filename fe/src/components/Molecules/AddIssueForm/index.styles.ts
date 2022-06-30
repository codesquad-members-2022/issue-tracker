import styled from 'styled-components';

export const StyledAddIssueForm = styled.div`
  display: inline-flex;

  img {
    margin: 4px 16px 0 0;
  }
`;

export const IssueForm = styled.div`
  width: 880px;

  form {
    width: 100%;
  }

  form + form {
    margin-top: 16px;
  }
`;

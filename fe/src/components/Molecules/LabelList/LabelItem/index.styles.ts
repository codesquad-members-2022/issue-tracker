import styled from 'styled-components';

export const LabelButtonWrapper = styled.div`
  display: flex;
  justify-content: flex-end;
  gap: 28px;
  button {
    color: ${({ theme }) => theme.colors.label};

    svg {
      stroke: ${({ theme }) => theme.colors.label};
    }

    &:hover {
      color: ${({ theme }) => theme.colors.primary.blue};

      svg {
        stroke: ${({ theme }) => theme.colors.primary.blue};
      }
    }
  }

  & > button:last-child {
    color: ${({ theme }) => theme.colors.error.red};

    svg {
      stroke: ${({ theme }) => theme.colors.error.red};
    }
  }
`;

export const ItemStyle = {
  padding: '36px 32px',
  display: 'grid',
  gridTemplateColumns: '20% 65% 15%',
};

export const LabelDesc = styled.span`
  color: ${({ theme }) => theme.colors.label};
  ${({ theme }) => theme.fontStyles.textSmall};
`;

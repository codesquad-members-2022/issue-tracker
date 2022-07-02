import styled from 'styled-components';

const Container = styled.div`
  width: 1280px;
  height: 64px;
  background: ${({ theme }) => theme.colors.greyScale.background};
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'space-between')};
  padding: 0 32px 0 32px;
  box-sizing: border-box;
`;

const Wrapper = styled.div`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center')};
  & > :not(:last-child) {
    margin-right: 22px;
  }
`;

export { Container, Wrapper };

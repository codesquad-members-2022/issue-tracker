import styled from 'styled-components';

const Container = styled.div`
  width: 1280px;
  height: 100px;
  background: ${({ theme }) => theme.colors.greyScale.offWhite};
  padding: 20px 32px 0 32px;
  box-sizing: border-box;
  ${({ theme }) => theme.mixins.flexBox('row', 'start', 'space-between')};
`;

const Wrapper1 = styled.div`
  ${({ theme }) => theme.mixins.flexBox('row', 'start', 'start')};
  input {
    margin-top: 8px;
    margin-right: 32px;
  }
`;

const Wrapper2 = styled.div``;

const Wrapper3 = styled.div`
  &:not(:last-child) {
    margin-bottom: 10px;
  }
  & > button:not(:last-child) {
    margin-right: 5px;
  }
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'start')};
`;

const Title = styled.h1`
  font-weight: 700;
  font-size: 18px;
  line-height: 32px;
  color: ${({ theme }) => theme.colors.greyScale.titleActive};
  margin-right: 14px;
`;

const Label = styled.div`
  width: 90px;
  height: 28px;
  font-weight: 500;
  font-size: 12px;
  line-height: 20px;
  background: ${({ color }) => color};
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center')};
  border-radius: 30px;
`;

const Content = styled.p`
  font-weight: 400;
  font-size: 16px;
  line-height: 28px;
  color: ${({ theme }) => theme.colors.greyScale.label};
  &:not(:last-child) {
    margin-right: 20px;
  }
`;

const Icon = styled.div`
  margin-right: 10px;
  margin-top: 20px;
`;

export { Container, Title, Label, Wrapper1, Wrapper2, Wrapper3, Content, Icon };

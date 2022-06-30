import styled from 'styled-components';
import CommonItem from 'components/Molecules/CommonList/CommonItem';

const StyledEmpthMsg = styled.p`
  width: 100%;
  padding: 28px;
  text-align: center;
  color: ${({ theme }) => theme.colors.titleActive};
  ${({ theme }) => theme.fontStyles.linkSmall};
`;

const EmptyItem = () => {
  return (
    <CommonItem>
      <StyledEmpthMsg>레이블을 추가해주세요!</StyledEmpthMsg>
    </CommonItem>
  );
};

export default EmptyItem;

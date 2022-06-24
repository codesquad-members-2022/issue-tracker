import * as S from 'components/milestone/styled/styled.mileStoneInput';

interface Props {
  labelText: string;
  placeholder: string;
}

function MileStoneInput({ labelText, placeholder }: Props) {
  return (
    <S.inputWrapper>
      <S.inputLabel>{labelText}</S.inputLabel>
      <S.input placeholder={placeholder} />
    </S.inputWrapper>
  );
}
export default MileStoneInput;

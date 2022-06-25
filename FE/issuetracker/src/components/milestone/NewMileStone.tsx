/* eslint-disable @typescript-eslint/no-empty-function */
import * as S from 'components/milestone/styled/styled.newMileStone';
import MileStoneInput from 'components/milestone/MileStoneInput';
import SaveButton from 'components/common/SaveButton';

function NewMileStone() {
  return (
    <S.newMileStoneWrapper>
      <S.Title>새로운 마일스톤 추가</S.Title>
      <S.inputTopWrapper>
        <MileStoneInput labelText="제목" placeholder="마일스톤 이름" />
        <MileStoneInput labelText="완료일(선택)" placeholder="완료일(선택) ex. YYYY-MM-DD" />
      </S.inputTopWrapper>
      <MileStoneInput labelText="설명(선택)" placeholder="설명(선택)" />
      <SaveButton buttonText="완료" margin="24px 0 0 0" handleButtonClick={() => {}} />
    </S.newMileStoneWrapper>
  );
}
export default NewMileStone;

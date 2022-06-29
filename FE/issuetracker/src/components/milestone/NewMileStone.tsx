import { useRecoilState } from 'recoil';
import * as S from 'components/milestone/styled/styled.newMileStone';
import MileStoneInput from 'components/milestone/MileStoneInput';
import SaveButton from 'components/common/SaveButton';
import { mileStone, mileStoneType, initialMileStone } from 'store/milestone';

interface Props {
  mileStoneData?: mileStoneType | undefined;
}

function NewMileStone({ mileStoneData = initialMileStone }: Props) {
  const [mileStoneState, setMileStoneState] = useRecoilState(mileStone);
  const title = mileStoneData?.title;
  const dueDate = mileStoneData?.dueDate;
  const description = mileStoneData?.description;
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    setMileStoneState({
      ...mileStoneState,
      [name]: value,
    });
  };

  const handleSaveButtonClick = () => {
    // 추후에 mileStoneState 데이터를 POST 요청할 것
  };

  return (
    <S.newMileStoneWrapper>
      <S.Title>새로운 마일스톤 추가</S.Title>
      <S.inputTopWrapper>
        <MileStoneInput
          inputName="title"
          labelText="제목"
          placeholder={title || '마일스톤 이름'}
          handleInputChange={handleInputChange}
        />
        <MileStoneInput
          inputName="dueDate"
          labelText="완료일(선택)"
          placeholder={dueDate || '완료일(선택) ex. YYYY-MM-DD'}
          handleInputChange={handleInputChange}
        />
      </S.inputTopWrapper>
      <MileStoneInput
        inputName="description"
        labelText="설명(선택)"
        placeholder={description || '설명(선택)'}
        handleInputChange={handleInputChange}
      />
      {mileStoneData && (
        <SaveButton
          buttonText="완료"
          margin="24px 0 0 0"
          handleButtonClick={handleSaveButtonClick}
        />
      )}
    </S.newMileStoneWrapper>
  );
}
export default NewMileStone;

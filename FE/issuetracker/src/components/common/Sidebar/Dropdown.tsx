import * as I from 'design/icons';
import * as S from 'components/common/Sidebar/styled/dropdown';
import { ReactNode, useState } from 'react';
import { accountsData, labelsData, mileStonesData } from 'data';
import { keyMaker } from 'utils/util';
import Label from '../Common';

type DropdownType = {
  subject: string;
};

function DropDown({ subject }: DropdownType) {
  const [isClicked, setIsClicked] = useState(false);
  function getDropDownContents(subject: string) {
    let contentsData;
    let content: ReactNode;
    switch (subject) {
      case '담당자':
        contentsData = accountsData;
        content = contentsData.map((content, idx) => {
          const key = keyMaker();
          return (
            <S.DropDownList key={key} idx={idx}>
              <S.DropDownContent>
                <S.SmallAcountImg src={content.profileImage} />
                {content.name}
              </S.DropDownContent>
              <I.offCheckCircle />
            </S.DropDownList>
          );
        });
        return content;
      case '레이블':
        contentsData = labelsData;
        content = contentsData.map((content, idx) => {
          const key = keyMaker();
          return (
            <S.DropDownList key={key} idx={idx}>
              <S.DropDownContent>
                <Label title={content.title} color={content.color} />
              </S.DropDownContent>
              <I.offCheckCircle />
            </S.DropDownList>
          );
        });
        return content;
      case '마일스톤':
        contentsData = mileStonesData;
        content = contentsData.map((content, idx) => {
          const key = keyMaker();
          return (
            <S.DropDownList key={key} idx={idx}>
              <S.DropDownContent>{content.title}</S.DropDownContent>
              <I.offCheckCircle />
            </S.DropDownList>
          );
        });
        return content;
      default:
        throw new Error();
    }
  }
  function toggleDropdown() {
    setIsClicked(!isClicked);
  }
  return (
    <S.PlusFigure
      onClick={() => {
        toggleDropdown();
      }}
    >
      <I.plus />
      <S.DropdownPanel isClicked={isClicked}>
        <S.DropDownTop>{subject} 추가</S.DropDownTop>
        <S.DropDownBottom>{getDropDownContents(subject)}</S.DropDownBottom>
      </S.DropdownPanel>
    </S.PlusFigure>
  );
}

export default DropDown;

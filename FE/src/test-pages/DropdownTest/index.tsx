import DropdownMenu from "@/components/common/DropdownMenu";
import * as S from "@/test-pages/DropdownTest/style";

const DropdownTest = () => {
  const dataFromServer = ["열린 이슈", "내가 작성한 이슈", "나에게 할당된 이슈", "내가 댓글을 남긴 이슈", "닫힌 이슈"];

  const TitleComponent = <S.DropdownMenuTitle>이슈 필터</S.DropdownMenuTitle>;

  const ListComponents = dataFromServer?.map((el, idx) => {
    return (
      <S.DropdownMenuItemBox key={idx}>
        <span>{el}</span>
        <input type="checkbox" />
      </S.DropdownMenuItemBox>
    );
  });

  return (
    <>
      <h1>Dropdown Test page</h1>
      <br />
      <S.ButtonBox>
        <DropdownMenu value="필터" titleComponent={TitleComponent} listComponents={ListComponents} width={240} />
        <DropdownMenu value="필터" titleComponent={TitleComponent} listComponents={ListComponents} width={240} />
      </S.ButtonBox>
      <S.ButtonBox>
        <DropdownMenu value="라벨" titleComponent={TitleComponent} listComponents={ListComponents} width={240} />
      </S.ButtonBox>
      <S.ButtonBox>
        <DropdownMenu value="마일스톤" titleComponent={TitleComponent} listComponents={ListComponents} width={240} />
      </S.ButtonBox>
    </>
  );
};

export default DropdownTest;

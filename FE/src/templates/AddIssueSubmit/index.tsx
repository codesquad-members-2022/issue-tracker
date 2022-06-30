import * as S from './style';
import { Button } from '@/components/common/Button';

export function AddIssueSubmit() {
  return (
    <S.AddIssueSubmit>
      <S.CancelButton>작성 취소</S.CancelButton>
      <Button color="primary" size="medium" fontSize="large">
        완료
      </Button>
    </S.AddIssueSubmit>
  );
}

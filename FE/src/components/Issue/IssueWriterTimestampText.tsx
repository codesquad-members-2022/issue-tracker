import { Text } from '@/components/common/Text';

type IssueWriterTimestampTextProps = {
  IssueWriter: string;
  IssueTimestamp: string;
};

// TODO: 날짜 계산 로직 어디에서 처리해줄것인가? 상위 템플릿 or 이 컴포넌트
export function IssueWriterTimestampText({
  IssueWriter,
  IssueTimestamp,
}: IssueWriterTimestampTextProps) {
  return (
    <Text
      text={`이 이슈가 ${IssueTimestamp} 전, ${IssueWriter}님에 의해 작성되었습니다. `}
      color="label"
    />
  );
}

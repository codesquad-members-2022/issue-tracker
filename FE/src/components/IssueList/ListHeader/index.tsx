import Button from '@/components/Button';
import { Icon } from '@/components/Icon';
import {
  $ListHeader,
  $IssueTabWrapper,
  $FilterMenuWrapper
} from '@/components/IssueList/ListHeader/style';

export default function ListHeader() {
  return (
    <$ListHeader>
      <$IssueTabWrapper>
        <Button styleType="mediumText">
          <Icon iconType="openLabel" />
          {`열린 이슈(2)`}
        </Button>
        <Button styleType="mediumText">
          <Icon iconType="closeLabel" />
          {`닫힌 이슈(0)`}
        </Button>
      </$IssueTabWrapper>
      <$FilterMenuWrapper>
        <Button styleType="mediumText">
          {'담당자'}
          <Icon iconType={'arrowDown'} />
        </Button>
        <Button styleType="mediumText">
          {'레이블'}
          <Icon iconType={'arrowDown'} />
        </Button>
        <Button styleType="mediumText">
          {'마일스톤'}
          <Icon iconType={'arrowDown'} />
        </Button>
        <Button styleType="mediumText">
          {'작성자'}
          <Icon iconType={'arrowDown'} />
        </Button>
      </$FilterMenuWrapper>
    </$ListHeader>
  );
}

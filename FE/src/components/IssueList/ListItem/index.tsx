import Button from '@/components/common/Button';
import { Icon } from '@/components/common/Icon';
import { $ListItem, $Contents, $Title, $Info, $Text } from '@/components/IssueList/ListItem/style';
import { ILabel, IListItem } from '@/components/IssueList/ListItem/type';
import Label from '@/components/common/Label';
import UserProfile from '@/components/common/UserProfile';

export default function ListItem({
  id,
  title,
  status,
  assignees,
  author,
  created_at,
  labels,
  milestone
}: IListItem) {
  return (
    <$ListItem>
      <$Contents>
        <$Title>
          <Icon iconType={status === 'OPEN' ? 'openLabel' : 'closeLabel'} />
          <$Text as="h3" size="large">
            {title}
          </$Text>
          {labels &&
            labels.map(({ id, name, color_code }: ILabel) => (
              <Label key={id} size="small" status="dark" background={color_code}>
                {name}
              </Label>
            ))}
        </$Title>
        <$Info>
          <$Text size="small">{`#${id}`}</$Text>
          <$Text as="p" size="small">{`이 이슈가 ${author?.name}님에 의해 작성되었습니다.`}</$Text>
          {milestone && (
            <Button styleType="mediumText" gap="8px" fontWeight="normal">
              <Icon iconType="milestone" />
              {milestone?.name}
            </Button>
          )}
        </$Info>
      </$Contents>
      <UserProfile src={author?.image_url} size="small" />
    </$ListItem>
  );
}

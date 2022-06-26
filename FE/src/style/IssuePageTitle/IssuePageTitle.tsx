import { Title } from './IssuePageTitle.styled';

export function IssuePageTitle({ children }: { children: string }) {
  return (
    <Title
      fontWeight="normal"
      fontSize="xl"
      lineHeight="taller"
      color="titleActive"
    >
      {children}
    </Title>
  );
}

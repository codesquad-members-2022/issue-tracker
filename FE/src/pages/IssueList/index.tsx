import Header from '@/components/Header';
import Layout from '@/layout';
import { $Contents } from '@/pages/IssueList/style';

export default function IssueList() {
  return (
    <Layout header={<Header />}>
      <$Contents />
    </Layout>
  );
}

import Logo from '@/components/Logo';
import Layout from '@/layout';
import { $Contents } from '@/pages/NotFound/style';
import { Link, Outlet } from 'react-router-dom';

export default function NotFound({ pageName }: { pageName: string }) {
  return (
    <Layout>
      <$Contents>
        <Logo type="large" title={`Not Found(${pageName})`} />
        <Outlet />
        <Link to="/">Go Home</Link>
      </$Contents>
    </Layout>
  );
}

import { Wrapper, Header, Contents, Footer } from '@/layout/style';

interface LayoutProps {
  header?: React.ReactNode;
  children: React.ReactNode;
  footer?: React.ReactNode;
}

function Layout({ header, children, footer, ...props }: LayoutProps) {
  return (
    <Wrapper {...props}>
      {header && <Header>{header}</Header>}
      <Contents>{children}</Contents>
      {footer && <Footer>{footer}</Footer>}
    </Wrapper>
  );
}

export default Layout;

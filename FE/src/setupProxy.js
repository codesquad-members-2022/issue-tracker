import { createProxyMiddleware } from 'http-proxy-middleware';

export default function (app) {
  app.use(
    '/login',
    createProxyMiddleware({
      target: 'http://louie-03.com',
      changeOrigin: true,
    }),
  );
}

import ReactDOM from 'react-dom/client';
import App from '@/App';

// TODO: !! API 테스트를 위해서 MSW 꺼놓음
if (process.env.NODE_ENV === 'development') {
  const { worker } = require('./mocks/browser');
  worker.start();
}

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement,
);

root.render(<App />);

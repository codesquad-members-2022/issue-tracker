import React from 'react';
import { Provider } from 'react-redux';
import AuthForm from '@components/Auth/AuthForm';
import { Header } from '@components/header';
import { AddIssue } from './pages/addIssue';
import IssueList from '@pages/issueList/IssueList';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import store from './store/store';
import Callback from '../src/components/Auth/Callback';

const App = () => {
  return (
    <Provider store={store}>
      <Router>
        <Routes>
          <Route path="/">
            <Route index element={<AuthForm />} />
            <Route path="issues" element={<IssueList />} />
            <Route path="addIssue" element={<AddIssue />} />
            <Route path="callback" element={<Callback />} />
          </Route>
        </Routes>
      </Router>
    </Provider>
  );
};

export default App;

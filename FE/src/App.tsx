import { Routes, Route } from 'react-router-dom';

import { Login } from 'pages/Login';
import { Issues } from 'pages/Issues';
import { NewIssue } from 'pages/NewIssue';
import { DetailIssue } from 'pages/DetailIssue';
import { Labels } from 'pages/Labels';
import { Milestones } from 'pages/Milestones';
import { Container } from 'component/Container';

import { worker } from './mocks/browser';

worker.start();

function App(): JSX.Element {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route element={<Container />}>
        <Route path="/issues" element={<Issues />} />
        <Route path="/newissue" element={<NewIssue />} />
        <Route path="/detailissue" element={<DetailIssue />} />
        <Route path="/labels" element={<Labels />} />
        <Route path="/milestones" element={<Milestones />} />
      </Route>
    </Routes>
  );
}

export default App;

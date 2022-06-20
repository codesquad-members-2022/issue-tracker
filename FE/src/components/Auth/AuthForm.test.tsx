import React from 'react';
import {
  render,
  screen,
  waitForElementToBeRemoved,
} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import AuthForm from './AuthForm';
import '@testing-library/jest-dom/extend-expect';

// focus가 아이디 input으로 가있고,
// 아래 로그인 버튼은 disabled

// const renderAuthForm = () => {
//   const result = render(<AuthForm />);

//   const Title = () =>
//     result.getByRole('heading', {
//       name: /issue tracker/i,
//     });
//   const GitHubLoginBtn = () => result.getByText(/깃허브로 로그인하기/);
//   const Divider = () => result.getByText(/or/i);
//   const EmailInput = () => result.getByPlaceholderText(/아이디/);
//   const passwordInput = () => result.getByPlaceholderText(/비밀번호/);
//   const SubmitBtn = () =>
//     result.getByRole('button', {
//       name: /아이디로 로그인/,
//     });
//   const SignUpBtn = () =>
//     result.getByRole('button', {
//       name: /회원 가입/,
//     });

//   return {
//     Title,
//     GitHubLoginBtn,
//     Divider,
//     EmailInput,
//     passwordInput,
//     SubmitBtn,
//     SignUpBtn,
//   };
// };

test('Initial rendering', () => {
  // input focus는 email
  // 로그인 버튼은 disabled
  render(<AuthForm />);
  const emailInput = screen.getByPlaceholderText('아이디');

  expect(emailInput).toHaveFocus();
});

// Wirte tests to ensure toHaveTextContent
// Checkbox is unchecked by default
// checkking checkbox enables button
// unchecking checkbox agin disables button

// Render the <SummaryFrom /> component

// Find checkbox and button using { name } option
// Use mockup for "name" option values

// import {
//     render,
//     screen,
//     waitForElementToBeRemoved,
//   } from '@testing-library/react';
//   import SummaryForm from '../SummaryForm';
//   import userEvent from '@testing-library/user-event';

//   test('Initial conditions', () => {
//     render(<SummaryForm />);
//     const checkbox = screen.getByRole('checkbox', {
//       name: /terms and conditions/i,
//     });
//     expect(checkbox).not.toBeChecked();

//     const confirmButton = screen.getByRole('button', { name: /confirm order/i });
//     expect(confirmButton).toBeDisabled();
//   });

//   test('Checkbox enables button on first click and disables on second click', () => {
//     render(<SummaryForm />);
//     const checkbox = screen.getByRole('checkbox', {
//       name: /terms and conditions/i,
//     });
//     const confirmButton = screen.getByText(/confirm order/i);
//     userEvent.click(checkbox);
//     expect(confirmButton).toBeEnabled();
//     // 이렇게 하니까 테스트가 안되었는데
//     userEvent.click(checkbox);
//     expect(confirmButton).toBeDisabled();
//   });

//   // test('popover responds to hover', () => {
//   //   render(<SummaryForm />);

//   //   // 로딩되었을때 popover는 나타나지 않음 hidden;
//   //   const nullPopover = screen.queryByText(
//   //     /No ice cream will actually be delivered/i,
//   //   );

//   //   expect(nullPopover).not.toBeInTheDocument();
//   //   // 무언가가 표시되지 않았을 때는 getByRole등의 쿼리를 사용할 수 없음.

//   //   // 체크박스 라벨로 올라가면 popover가 보임
//   //   const termsAndConditions = screen.getByText(/terms and conditions/i);
//   //   userEvent.hover(termsAndConditions);

//   //   const popover = screen.getByText(/No ice cream will actually be delivered/i);
//   //   expect(popover).toBeInTheDocument();
//   //   // 이거 필수적일까?
//   //   // getBy는 매치가 없을 경우 오류를 발생시킴.
//   //   // getBy로 찾을수 있다는 것 자체가 다큐먼트 안에 있다는 것임.
//   //   // 그래서 아래 단언이 필요하지 않음, 다만 가독성과 테스트의 목적을 알려주므로
//   //   // 사용하는게 좋음. :54번 라인

//   //   userEvent.unhover(termsAndConditions);
//   //   const nullPopoverAgain = screen.queryByText(
//   //     /No ice cream will actually be delivered/i,
//   //   );
//   //   expect(nullPopoverAgain).not.toBeInTheDocument();
//   //   // 커서를 마우스 밖으로 옮기면 disappears
//   // });

//   // 비동기업데이트가 일어날때 오류
//   // was no wrapped in act
//   // 리액트의 상태가 변경되는 부분을 act로 사용하는 것이 좋다고 함
//   // 근데 이건 오류를 사용하지 않는게 좋음
//   // test 함수가 이미 종료된 이후에 일부 비동기 업데이트가 일어나서 문제가 된 것.
//   // act로 행동을 래핑하면 안됨.
//   // 이미 래핑되어있기 때문임. (링크 url);

//   // g해결
//   // 테스트가 끝난지 무엇이 가장 먼저 바꼈는지 알아야함
//   // 변경을 기다린 뒤 단언을 함으로써,
//   // 그 변경을 처리해야함.

//   test('popover responds to hover', async () => {
//     render(<SummaryForm />);

//     const nullPopover = screen.queryByText(
//       /No ice cream will actually be delivered/i,
//     );

//     expect(nullPopover).not.toBeInTheDocument();

//     const termsAndConditions = screen.getByText(/terms and conditions/i);
//     userEvent.hover(termsAndConditions);

//     const popover = screen.getByText(/No ice cream will actually be delivered/i);
//     expect(popover).toBeInTheDocument();

//     // 문제가 된 지점, 팝오버가 해제된 부분을 알지 못함.
//     // waitForElementToBeRemoved를 사용해야함.
//     userEvent.unhover(termsAndConditions);
//     await waitForElementToBeRemoved(() =>
//       screen.queryByText(/No ice cream will actually be delivered/i),
//     );
//   });

//   // 테스트가 통과할 수 없었던 이유는, 팝오버가 사라지는 동작이 비동기적을 진행되었기 때문임.
//   // 단언을 비동기함으로써 문제를 해결할 수 있었음.

import { IMember } from '@/components/IssueList/ListItem/type';

const getCurrentUserInfo = () => {
  const currentUserInfo = localStorage.getItem('currentUserInfo');

  if (!currentUserInfo) return;

  const userInfoObject: IMember = JSON.parse(currentUserInfo);

  return userInfoObject;
};

const getCurrentUserInfoOf = (infoType: keyof IMember) => {
  const userInfoObject = getCurrentUserInfo();

  if (!userInfoObject) return null;

  const { [infoType]: userData } = userInfoObject;

  return userData;
};

export { getCurrentUserInfo, getCurrentUserInfoOf };

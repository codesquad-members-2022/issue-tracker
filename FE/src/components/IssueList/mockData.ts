import { IListItem } from '@/components/IssueList/ListItem/type';

const mockData: IListItem[] = [
  {
    title: '이슈 제목',
    labelList: ['레이블 이름', 'documentation', 'FE'],
    number: 4,
    timestamp: '',
    milestone: '마스터즈 코스',
    status: 'OPEN',
    author: {
      name: 'anonymous',
      userId: 'anonymous',
      profile:
        'https://images.unsplash.com/profile-fb-1620954106-6ea0901e5361.jpg?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128'
    }
  },
  {
    title: '[FE] 이슈 리스트 페이지 레이아웃 구현',
    labelList: ['FE', 'design'],
    number: 3,
    timestamp: '',
    milestone: '마스터즈 코스',
    status: 'OPEN',
    author: {
      name: 'Hemdi',
      userId: 'Hemudi',
      profile: 'https://avatars.githubusercontent.com/u/34249911?v=4'
    }
  },
  {
    title: '[FE] TextInput 컴포넌트 글자수 체크 기능 구현',
    labelList: ['FE', 'feature'],
    number: 2,
    timestamp: '',
    milestone: '마스터즈 코스',
    status: 'OPEN',
    author: {
      name: 'Dony',
      userId: 'jindonyy',
      profile: 'https://avatars.githubusercontent.com/u/17706346?v=4'
    }
  },
  {
    title: '[BE] GitHub과의 OAuth 통신 구현',
    labelList: ['BE', 'feature'],
    number: 1,
    timestamp: '',
    milestone: '마스터즈 코스',
    status: 'CLOSE',
    author: {
      name: 'Sammy',
      userId: 'astraum',
      profile: 'https://avatars.githubusercontent.com/u/94687862?v=4'
    }
  }
];

export default mockData;

import { MockData } from '@/components/IssueList/ListItem/type';

const mockData: MockData = {
  issueList: [
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
  ],
  labelList: [
    {
      id: 1,
      name: 'feature',
      description: 'feature에 관련된 무언가',
      color_code: '#c5def5'
    },
    {
      id: 2,
      name: 'documentation',
      description: '문서작업은 중요해',
      color_code: '#D97E0A'
    }
  ],
  milestoneList: [
    {
      id: 1,
      name: '첫번째 마일스톤',
      description: '마일스톤 마일스톤 첫첫 마일스톤',
      target_date: '2022-06-30',
      open_issue: 1,
      closed_issue: 1
    }
  ]
};

export default mockData;

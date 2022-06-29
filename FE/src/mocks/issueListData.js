const issueListData = {
  opened: {
    labelCount: 3,
    milestoneCount: 2,
    openedIssues: 2,
    closedIssues: 1,
    issues: [
      {
        id: 1,
        title: '제목1',
        createdTime: '2022-06-23 12:12:13',
        writer: '글쓴이1',
        labels: [
          {
            name: '라벨네임',
            color: {
              backgroundColor: '#000000',
              textColor: '#FFFFFF'
            }
          },
          {
            name: '라벨네임1',
            color: {
              backgroundColor: '#000000',
              textColor: '#FFFFFF'
            }
          }
        ],
        milestoneName: '마일스톤1',
        status: 'OPEN'
      },
      {
        id: 3,
        title: '제목3',
        createdTime: '2021-06-22 20:12:13',
        writer: '글쓴이3',
        labels: [
          {
            name: '라벨네임',
            color: {
              backgroundColor: '#000000',
              textColor: '#FFFFFF'
            }
          },
          {
            name: '라벨네임1',
            color: {
              backgroundColor: '#000000',
              textColor: '#FFFFFF'
            }
          }
        ],
        milestoneName: null,
        status: 'OPEN'
      }
    ]
  },
  closed: {
    labelCount: 3,
    milestoneCount: 2,
    openedIssues: 2,
    closedIssues: 1,
    issues: [
      {
        id: 2,
        title: '제목2',
        createdTime: '2022-06-22 12:12:13',
        writer: '글쓴이2',
        labels: null,
        milestoneName: '마일스톤2',
        status: 'CLOSE'
      }
    ]
  },
  all: {
    labelCount: 3,
    milestoneCount: 2,
    openedIssues: 2,
    closedIssues: 1,
    issues: [
      {
        id: 1,
        title: '제목1',
        createdTime: '2022-06-23 12:12:13',
        writer: '글쓴이1',
        labels: [
          {
            name: '라벨네임',
            color: {
              backgroundColor: '#000000',
              textColor: '#FFFFFF'
            }
          },
          {
            name: '라벨네임1',
            color: {
              backgroundColor: '#000000',
              textColor: '#FFFFFF'
            }
          }
        ],
        milestoneName: '마일스톤1',
        status: 'OPEN'
      },
      {
        id: 2,
        title: '제목2',
        createdTime: '2022-06-22 12:12:13',
        writer: '글쓴이2',
        labels: null,
        milestoneName: '마일스톤2',
        status: 'CLOSE'
      },
      {
        id: 3,
        title: '제목3',
        createdTime: '2021-06-22 20:12:13',
        writer: '글쓴이3',
        labels: [
          {
            name: '라벨네임',
            color: {
              backgroundColor: '#000000',
              textColor: '#FFFFFF'
            }
          },
          {
            name: '라벨네임1',
            color: {
              backgroundColor: '#000000',
              textColor: '#FFFFFF'
            }
          }
        ],
        milestoneName: null,
        status: 'OPEN'
      }
    ]
  }
};

export default issueListData;

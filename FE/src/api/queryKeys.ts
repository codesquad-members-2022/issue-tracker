const issueListQueryKeys = {
  all: ['issueList'],
  open: ['issueList', { status: 'open' }],
  close: ['issueList', { status: 'close' }]
};

const issueListCountQueryKeys = {
  all: ['issueList', 'count'],
  open: ['issueList', { status: 'open' }, 'count'],
  close: ['issueList', { status: 'close' }, 'count']
};

export { issueListQueryKeys, issueListCountQueryKeys };

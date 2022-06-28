const issueListQueryKeys = {
  all: ['issue'],
  open: ['issue', { status: 'open' }],
  close: ['issue', { status: 'close' }]
};

const issueCountQueryKeys = {
  all: ['issue', 'count'],
  open: ['issue', { status: 'open' }, 'count'],
  close: ['issue', { status: 'close' }, 'count']
};

const labelListQueryKeys = ['label'];

const labelCountQueryKeys = ['label', 'count'];

const milestoneListQueryKeys = ['milestone'];

const milestoneCountQueryKeys = ['milestone', 'count'];

export {
  issueListQueryKeys,
  issueCountQueryKeys,
  labelListQueryKeys,
  labelCountQueryKeys,
  milestoneListQueryKeys,
  milestoneCountQueryKeys
};

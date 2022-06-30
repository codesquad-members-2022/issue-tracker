const issueListQueryKeys = {
  all: ['issue'],
  open: ['issue', { status: 'open' }],
  closed: ['issue', { status: 'closed' }]
};

const issueCountQueryKeys = {
  all: ['issue', 'count'],
  open: ['issue', { status: 'open' }, 'count'],
  closed: ['issue', { status: 'closed' }, 'count']
};

const labelListQueryKeys = ['label'];

const labelCountQueryKeys = ['label', 'count'];

const milestoneListQueryKeys = ['milestone'];

const milestoneCountQueryKeys = ['milestone', 'count'];

const memberListQueryKeys = ['members'];

const currentMemberQueryKeys = ['members', 'current'];

export {
  issueListQueryKeys,
  issueCountQueryKeys,
  labelListQueryKeys,
  labelCountQueryKeys,
  milestoneListQueryKeys,
  milestoneCountQueryKeys,
  memberListQueryKeys,
  currentMemberQueryKeys
};

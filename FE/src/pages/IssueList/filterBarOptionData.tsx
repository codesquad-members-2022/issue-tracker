import { Icon } from '@/components/common/Icon';
import { getCurrentUserInfo } from '@/utils/user';
import { Option } from '@/components/common/Dropdown/Panel/type';

const radioIcon = {
  off: <Icon iconType="radioOff" />,
  on: <Icon iconType="radioOn" />
};

const FILTER_BAR_OPTIONS = ((): Option[] => {
  const { id, user_id } = getCurrentUserInfo();
  return [
    {
      children: '열린 이슈',
      radio: radioIcon,
      value: 'opened',
      filterCondition: {
        status: 'open',
        assignee: null,
        label: null,
        milestone: null,
        author: null,
        comment: null
      }
    },
    {
      children: '내가 작성한 이슈',
      radio: radioIcon,
      value: 'written',
      filterCondition: {
        status: 'open',
        author: {
          id,
          name: user_id
        },
        assignee: null,
        label: null,
        milestone: null,
        comment: null
      }
    },
    {
      children: '나에게 할당된 이슈',
      radio: radioIcon,
      value: 'assigned',
      filterCondition: {
        status: 'open',
        assignee: {
          id,
          name: user_id
        },
        label: null,
        milestone: null,
        author: null,
        comment: null
      }
    },
    {
      children: '내가 댓글을 남긴 이슈',
      radio: radioIcon,
      value: 'comments',
      filterCondition: {
        status: 'open',
        comment: {
          id,
          name: user_id
        },
        assignee: null,
        label: null,
        milestone: null,
        author: null
      }
    },
    {
      children: '닫힌 이슈',
      radio: radioIcon,
      value: 'closed',
      filterCondition: {
        status: 'closed',
        assignee: null,
        label: null,
        milestone: null,
        author: null,
        comment: null
      }
    }
  ];
})();

export { FILTER_BAR_OPTIONS };

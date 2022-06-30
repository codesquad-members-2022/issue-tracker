import { useQuery } from 'react-query';
import { useParams } from 'react-router-dom';
import axios from 'axios';

import { useGetLabelData } from 'api/label';
import useGetMilestoneData from 'api/milestone';
import useGetMembersData from 'api/member';

import styled from 'styled-components';
import Comment from 'components/Molecules/Comment';
import IssueHeader from 'components/Molecules/IssueHeader';
import SideBar from 'components/Molecules/SideBar';

import { UserTypes, LabelTypes, MilestoneTypes } from 'components/types';

const Divider = styled.div`
  width: 100%;
  height: 1px;
  margin: 32px 0;
  background: ${({ theme }) => theme.colors.line};
`;

const Content = styled.div`
  display: flex;
  justify-content: space-around;
`;

const CommentList = styled.div`
  & > div + div {
    margin-top: 24px;
  }

  & > div:last-child {
    margin-top: 40px;
  }
`;

interface ReactionTypes {
  emoji: string;
  members: UserTypes[];
}
interface CommentsTypes {
  content: string;
  createdAt: string;
  updatedAt: string;
  writer: UserTypes;
  reactions: ReactionTypes[];
}
export interface IssueDetailDataTypes {
  id: number;
  status: 'OPEN' | 'CLOSED';
  title: string;
  writer: UserTypes;
  assignees: UserTypes[];
  labels: LabelTypes[];
  milestone: MilestoneTypes;
  comments: CommentsTypes[];
}

const IssueDetail = () => {
  const { issueId } = useParams();

  const getServerData = async (URL: string): Promise<IssueDetailDataTypes> => {
    const { data } = await axios.get(`${process.env.REACT_APP_SERVER_URL}/${URL}`);
    return data;
  };

  const { isLoading, data, error } = useQuery(`issueDetail${issueId}`, () => getServerData(`api/issues/${issueId}`), {
    cacheTime: Infinity,
  });

  const { isLoading: labelsDataIsLoading, data: labelData, error: labelsDataError } = useGetLabelData();
  const { isLoading: milestonesDataIsLoading, data: milestoneData, error: milestonesDataError } = useGetMilestoneData();
  const { isLoading: membersDataIsLoading, data: memberData, error: membersDataError } = useGetMembersData();

  if (isLoading || labelsDataIsLoading || milestonesDataIsLoading || membersDataIsLoading) return <div>loading</div>;
  if (error || labelsDataError || milestonesDataError || membersDataError) return <div>error</div>;
  if (!data || !labelData || !milestoneData || !memberData) return <div>데이터가 없습니다</div>;

  const userInfo = localStorage.getItem('userInfo');
  const { id } = JSON.parse(userInfo!);

  const isAssignee = data.assignees.some((el: UserTypes) => el.id === id);

  // console.log(data);

  return (
    <>
      <div>
        <IssueHeader
          id={data.id}
          status={data.status}
          title={data.title}
          isEditer={isAssignee}
          writer={data.writer}
          commentCount={data.comments.length}
          createTime="2022-06-26T15:41:03.730526"
        />
        <Divider />
      </div>
      <Content>
        <CommentList>
          {data.comments.map((comment) => (
            <Comment
              key={`${comment.createdAt}-${comment.content}`}
              isOpen
              isIssueWriter={data.writer.id === comment.writer.id}
              isEditer={comment.writer.id === id}
              comments={comment}
            />
          ))}
          <Comment isNewComment isOpen isWriter />
        </CommentList>
        <SideBar
          isEditer={isAssignee}
          sideBarList={[
            {
              id: 1,
              type: 'ASSIGN',
              indicatorLabel: '담당자',
              dropdownTitle: '담당자 필터',
              dropdownList: memberData.members,
              contentList: data.assignees,
            },
            {
              id: 2,
              type: 'LABEL',
              indicatorLabel: '레이블',
              dropdownTitle: '레이블 필터',
              dropdownList: labelData.labels,
              contentList: data.labels,
            },
            {
              id: 3,
              type: 'MILESTONE',
              indicatorLabel: '마일스톤',
              dropdownTitle: '마일스톤 필터',
              dropdownList: milestoneData.milestones,
              panelType: 'radio',
              contentList: [data.milestone],
            },
          ]}
        />
      </Content>
    </>
  );
};

export default IssueDetail;

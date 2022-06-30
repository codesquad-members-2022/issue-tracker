import { ComponentStory, ComponentMeta } from '@storybook/react';
import Comment from 'components/Molecules/Comment';

export default {
  title: 'Molecules/Comment',
  component: Comment,
} as ComponentMeta<typeof Comment>;

const Template: ComponentStory<typeof Comment> = (args) => <Comment {...args} />;

export const Writer = Template.bind({});
Writer.args = {
  isWriter: true,
  isOpen: true,
  comments: {
    writer: {
      id: 1,
      loginId: 'jeremy0405',
      profileImageUrl:
        'https://post-phinf.pstatic.net/MjAyMTA2MDRfOTAg/MDAxNjIyNzcyMjY1NzQ2.sRxvXF_CKk6NqfiAI6624veOffmu7GDJmXuoMcmgQv0g.ZA1bWEuLFT7--CvzUFZ6TXN2TWX6rhze_t7ilqwk-tcg.JPEG/IMG_3115.jpg?type=w1200',
    },
    createdAt: '2022-06-15T00:00:00',
    updatedAt: '2022-06-15T00:00:00',
    content: '헝그리',
  },
};

export const Editable = Template.bind({});
Editable.args = {
  ...Writer.args,
};

export const NewComment = Template.bind({});
NewComment.args = {
  ...Editable.args,
  isNewComment: true,
};

export const NoWriter = Template.bind({});
NoWriter.args = {
  ...Writer.args,
  isWriter: false,
};

export const Close = Template.bind({});
Close.args = {
  ...Writer.args,
  isWriter: true,
  isOpen: false,
};

import { atom } from 'recoil';
import AccountSrc from 'assets/images/UserImageLarge.svg';
import { MileStoneType, LabelType, AccountType } from 'data';

type WriterType = {
  name: string;
  imgUrl: string;
};
type CommentType = {
  writer: WriterType;
  writtenTime: string;
  description: string;
};

type newIssue = {
  title: string;
  writer: WriterType;
  writtenTime: string;
  comments: Array<CommentType>;
  mileStone: MileStoneType;
  labels: LabelType;
  assignees: AccountType;
};

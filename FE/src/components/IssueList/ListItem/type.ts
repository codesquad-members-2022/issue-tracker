interface IAuthor {
  userId: string;
  name: string;
  profile: string;
}

interface IListItem {
  title: string;
  labelList: string[];
  number: number;
  author: IAuthor;
  timestamp: string;
  milestone: string;
  status: 'OPEN' | 'CLOSE';
}

interface I$Text {
  size: 'large' | 'small';
}

export type { IListItem, I$Text };

interface IMemberData {
  id: number;
  user_id: string;
  image_url: string;
}

interface ILabelData {
  id: number;
  name: string;
  description: string;
}

interface IMilestoneData extends ILabelData {}

export type { IMemberData, ILabelData, IMilestoneData };

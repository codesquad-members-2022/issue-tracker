interface IMemberData {
  user_id: string;
  image_url: string;
}

interface ILabelData {
  name: string;
  description: string;
}

interface IMilestoneData extends ILabelData {}

export type { IMemberData, ILabelData, IMilestoneData };

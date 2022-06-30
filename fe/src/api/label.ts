import axios from 'axios';
import { useQuery } from 'react-query';
import { LabelTypes } from 'components/Atoms/Label';
import { getServerData } from 'api/issue';

export const useGetLabelData = () => {
  return useQuery('labels', () => getServerData('api/labels'), {
    cacheTime: Infinity,
  });
};

export const addLabel = async (newLabel: LabelTypes): Promise<LabelTypes> => {
  const { id, ...anotherInfo } = newLabel;

  const { data: addLabelData } = await axios.post<LabelTypes>(
    `${process.env.REACT_APP_SERVER_URL}/api/labels`,
    anotherInfo,
  );
  return addLabelData;
};

export const replaceLabel = async (label: LabelTypes): Promise<LabelTypes> => {
  const { id, ...anotherInfo } = label;

  const { data: replaceLabelData } = await axios.patch<LabelTypes>(
    `${process.env.REACT_APP_SERVER_URL}/api/labels/${id}`,
    anotherInfo,
  );
  return replaceLabelData;
};

export const deleteLabel = async (deletedLabelId: number): Promise<boolean> => {
  const { data: deleteLabelData } = await axios.delete<boolean>(
    `${process.env.REACT_APP_SERVER_URL}/api/labels/${deletedLabelId}`,
    { data: deletedLabelId },
  );
  return deleteLabelData;
};

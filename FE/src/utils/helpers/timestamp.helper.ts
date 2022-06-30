export const getDiffrentMinutes = (timestamp: string): number => {
  const now = new Date();
  const copiedTimestamp = new Date(timestamp);

  const differentMinutes =
    (now.getTime() - copiedTimestamp.getTime()) / (1000 * 60);

  return Math.floor(differentMinutes);
};

export const getTrimmedMessage = (minutes: number): string => {
  if (minutes < 1) {
    return '1분 전';
  }
  if (minutes < 60) {
    return `${minutes}분`;
  }
  if (minutes < 1440) {
    return `${Math.floor(minutes / 60)}시간`;
  }
  return `${Math.floor(minutes / 1440)}일`;
};

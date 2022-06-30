type filterBooleanCountProps = {
  (array: [], target: string, boolean: boolean): number;
};

export const filterBooleanCount: filterBooleanCountProps = (
  array,
  target,
  boolean,
) => {
  const count = array.filter(value => value[target] === boolean);

  return count.length;
};

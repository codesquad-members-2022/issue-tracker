const debounce = (() => {
  let timerID: number;

  return (fn: Function, ms: number) => {
    if (timerID) clearTimeout(timerID);
    timerID = setTimeout(fn, ms);
  };
})();

export default debounce;

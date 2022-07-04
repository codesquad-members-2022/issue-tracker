export const getTimeStamp = (createdTime: string) => {
  let timeStamp;

  const createdTimeDate = new Date(createdTime);
  const today = new Date();

  const createdDate = {
    year: createdTimeDate.getFullYear(),
    month: createdTimeDate.getMonth(),
    date: createdTimeDate.getDate(),
    hour: createdTimeDate.getHours(),
    min: createdTimeDate.getMinutes(),
    sec: createdTimeDate.getSeconds()
  };
  const todayDate = {
    year: today.getFullYear(),
    month: today.getMonth(),
    date: today.getDate(),
    hour: today.getHours(),
    min: today.getMinutes(),
    sec: today.getSeconds()
  };

  switch (true) {
    case createdDate.year !== todayDate.year:
      timeStamp = `${createdDate.year}년 ${createdDate.month + 1}월 ${
        createdDate.date
      }일`;
      break;
    case createdDate.month !== todayDate.month:
      timeStamp = `${createdDate.month + 1}월 ${createdDate.date}일`;
      break;
    case createdDate.date !== todayDate.date:
      timeStamp = `${todayDate.date - createdDate.date}일 전`;
      break;
    case createdDate.hour !== todayDate.hour:
      timeStamp = `${todayDate.hour - createdDate.hour}시간 전`;
      break;
    case createdDate.min !== todayDate.min:
      timeStamp = `${todayDate.min - createdDate.min}분 전`;
      break;
    default:
      timeStamp = `${todayDate.sec - createdDate.sec}초 전`;
  }

  return timeStamp;
};

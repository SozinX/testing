let time = document.getElementById("timer").value;
let secondsOfStart = parseInt(document.getElementById("hours").value) * 3600 + parseInt(document.getElementById("minutes").value) * 60 + parseInt(document.getElementById("seconds").value);
let currentTime = 0;
let hour = {
    timeZone: 'Europe/Kyiv',
    hour: 'numeric',
  },
  hours = new Intl.DateTimeFormat([], hour);
let minute = {
    timeZone: 'Europe/Kyiv',
    minute: 'numeric',
  },
  minutes = new Intl.DateTimeFormat([], minute);
  let second = {
      timeZone: 'Europe/Kyiv',
      second: 'numeric',
    },
    seconds = new Intl.DateTimeFormat([], second);
setInterval(() => {
  currentTime = parseInt(hours.format(new Date())) * 3600 + parseInt(minutes.format(new Date())) * 60 + parseInt(seconds.format(new Date()));
  difference = Math.abs(currentTime - secondsOfStart);
  document.getElementById("countDown").value = "Time left: " + Math.trunc((time-difference)/60) + ":" + (time-difference)%60;
  if(difference >= time){
    window.location.replace("/result/" + document.getElementById("testId").value);
  }
}, 1000);

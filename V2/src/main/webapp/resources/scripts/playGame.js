var startTime = 0;
var endTime = 0;
var timerStarted = false;
function startTimer(){
	if(!timerStarted){
    	startTime = Date.now();
    	document.getElementById("note").innerHTML = "Note: Timer started. If you click update before ending the timer your game will NOT be updated, and your time will be lost!";
    	timerStarted = true;
    }
}

function endTimer(){
    if(startTime != 0 && timerStarted){
        endTime = Date.now();
	    var timePlayedMil = endTime - startTime;
	//    var timePlayedSec = timePlayedMil / 1000;
	//    var timePlayedMin = timePlayedSec / 60;
	    var timePlayedHours = timePlayedMil / 1000;
	    document.getElementById("Time this session").value = timePlayedHours;
	    document.getElementById("note").innerHTML = "Note: Timer finished. Press update to add time to your game. If you start the timer again before clicking update your time will be lost!";
	    timerStarted = false;
	}
}

var interval = setInterval(gameLoop, 250);

var cardNum = ["1", "2", "3", "4", "5"];

function gameLoop() {
    
    // call for JSON, get data
    $.getJSON( "/blackjack/v1.0/join") 
    
    // update board (whose turn it is)
    // call write_p1 through p6, dealer, alert
    
    document.getElementById("p1").innerHTML = "New text!";
    document.getElementById("p2").innerHTML = "New text!";
    document.getElementById("p3").innerHTML = "New text!";
    document.getElementById("p4").innerHTML = "New text!";
    document.getElementById("p5").innerHTML = "New text!";
    document.getElementById("p6").innerHTML = "New text!";
    
    //if (player must wait) {
        //wait
    //}
    //else { // it is the players turn
        // process players turn  
        // send JSON for turn    
    //}
    
}

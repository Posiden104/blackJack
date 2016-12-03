var message = '';
var playerName = "ThisGuy6";
var playerScore = "100";

var playerHand = ["KC", "6C", "3C"];
var cardNum = ["1", "2", "3", "4", "5"];
var handSize = playerHand.length;

//Check if Google is in the watchlist
if (1) {
    message = '<div class="panel-body">'  + playerName + '</div><div class="panel-score">' + playerScore + '</div>';
    
    for (i = 0; i < handSize; i++) {
        
        message = message + '<img class="card' + cardNum[i] + '" src="pics/' + playerHand[i] + '.png" class="img-thumbnail" alt="Card1">';
    }
    
        document.write(message);
}

else {
    message = 'No Data Available';
    document.write(message);
}
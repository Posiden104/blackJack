var message = '';
var playerName = "ThisGuy";
var playerScore = "100";
var playerBet = "$100.00";

var playerHand = ["KD", "6H", "3S"];
var cardNum = ["1", "2", "3", "4", "5"];
var handSize = playerHand.length;

//Check if Google is in the watchlist
if (1) {
    message = '<div class="panel-body">'  + playerName + '</div><div class="panel-score">' + playerScore + '</div><div class="panel-bet">' + playerBet + '</div>';
    
    for (i = 0; i < handSize; i++) {
        
        message = message + '<img class="card' + cardNum[i] + '" src="pics/' + playerHand[i] + '.png" class="img-thumbnail" alt="Card1">';
    }
    
        document.write(message);
}
else {
    message = 'No Data Available';
    document.write(message);
}
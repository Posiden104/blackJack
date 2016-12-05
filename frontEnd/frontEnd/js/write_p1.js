
var message = '';
var playerName = "SomeGuy";

if(player.playerID == 1) {
	playerName = "Player 1";
}


var playerHand = player.hand;
var cardNum = ["1", "2", "3", "4", "5"];
var handSize = playerHand.length;

//Check if Google is in the watchlist
if (1) {
    message = '<div class="panel-body">'  + playerName + '</div><div class="panel-score">' + player.Score + '</div><div class="panel-bet">' + player.bet + '</div>';
    
    for (i = 0; i < handSize; i++) {
        
        message = message + '<img class="card' + cardNum[i] + '" src="pics/' + playerHand[i] + '.png" class="img-thumbnail" alt="Card1">';
    }
    
        document.write(message);
}
else {
    message = 'No Data Available';
    document.write(message);
}

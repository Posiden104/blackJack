
var message = '';

if(players[4] == null) {
    message = 'No Data Available';
    document.write(message);
}
else {
    
    var playerName = "Player 4"
    var playerHand = players[4].hand;
    // alert(playerHand[0])
    var cardNum = ["1", "2", "3", "4", "5"];
    var handSize = playerHand.length;

    message = '<div class="panel-body">'  + playerName + '</div><div class="panel-score">' + players[4].bet + '</div><div class="panel-bet">' + players[4].money + '</div><div class="panel-default">' + players[4].status  + '</div>';
    
    for (i = 0; i < handSize; i++) {
        
            message = message + '<img class="card' + cardNum[i] + '" src="pics/' + playerHand[i] + '.png" class="img-thumbnail" alt="Card1">';
    }
    
    document.getElementById("p5").innerHTML = message;
      
}	



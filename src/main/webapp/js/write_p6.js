
var message = '';

if(players[5] == null) {
    message = 'No Data Available';
    document.write(message);
}
else {
    
    var playerName = "Player 4"
    var playerHand = players[5].hand;
    // alert(playerHand[0])
    var cardNum = ["1", "2", "3", "4", "5"];
    var handSize = playerHand.length;

    message = '<div class="panel-body">'  + playerName + '</div><div class="panel-score">' + players[5].bet + '</div><div class="panel-bet">' + '</div><div class="panel-default">' + players[5].status  + '</div>';
    
    for (i = 0; i < handSize; i++) {
        
            message = message + '<img class="card' + cardNum[i] + '" src="pics/' + playerHand[i] + '.png" class="img-thumbnail" alt="Card1">';
    }
    
    document.getElementById("p6").innerHTML = message;
      
}	



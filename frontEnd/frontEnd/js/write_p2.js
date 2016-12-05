
var message = '';

if(players[1] == null) {
    message = 'No Data Available';
    document.write(message);
}
else {
    
    var playerName = "Player 2"
    var playerHand = players[1].hand;
    // alert(playerHand[0])
    var cardNum = ["1", "2", "3", "4", "5"];
    var handSize = playerHand.length;

    message = '<div class="panel-body">'  + playerName + '</div><div class="panel-score">' + players[1].Score + '</div><div class="panel-bet">' + players[1].bet + '</div><div class="panel-bet">' + players[1].money + '</div><div class="panel">' + players[1].status '</div>';
    
    for (i = 0; i < handSize; i++) {
        
            message = message + '<img class="card' + cardNum[i] + '" src="pics/' + playerHand[i] + '.png" class="img-thumbnail" alt="Card1">';
    }
    
    document.getElementById("p2").innerHTML = message;
      
}	




var message = '';

if(players[3] == null) {
    message = 'No Data Available';
    document.write(message);
}
else {
    
    var playerName = "Player 4"
    var playerHand = players[3].hand;
    // alert(playerHand[0])
    var cardNum = ["1", "2", "3", "4", "5"];
    var handSize = playerHand.length;

    message = '<div class="panel-body">'  + playerName + '</div><div class="panel-bet">' + players[3].bet + '</div><div class="panel-bet">' + players[3].money + '</div><div class="panel-default">' + players[3].status  + '</div>';
    
    for (i = 0; i < handSize; i++) {
        
            message = message + '<img class="card' + cardNum[i] + '" src="pics/' + playerHand[i] + '.png" class="img-thumbnail" alt="Card1">';
    }
    
    document.getElementById("p4").innerHTML = message;
      
}	



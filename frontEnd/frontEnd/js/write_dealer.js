
var message = '';

if(dealerhand[0] == null) {
    message = 'Dealer';
    document.write(message);
}
else {
    
    var playerName = "Dealer"
    var playerHand = dealerhand;
    // alert(playerHand[0])
    var cardNum = ["1", "2", "3", "4", "5"];
    var handSize = playerHand.length;

    // message = '<div class="panel-body">'  + playerName + '</div><div class="panel-score">' + '<div>';
    
    for (i = 0; i < handSize; i++) {
        
            message = message + '<img class="card' + cardNum[i] + '" src="pics/' + dealerhand[i] + '.png" class="img-thumbnail" alt="Card1">';
    }
    
    document.getElementById("d1").innerHTML = message;
      
}	



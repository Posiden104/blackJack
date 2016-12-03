var message = '';
var playerName = "ThisGuy";
var playerScore = "100";

var playerHand = ["KC", "6C"];
var handSize = playerHand.length;

//Check if Google is in the watchlist
if (1) {
    message = '<div class="panel-body">'  + playerName + '</div><div class="panel-score">' + playerScore + '</div>';
    
    for (i = 0; i < handSize; i++) {
        message = message + '<img class="card' + i+1 + '" src="pics/' + playerHand[i] + '.png" class="img-thumbnail" alt="Card' + i+1 + '">';
    }
    
    //smessage = message + '<img class="card1" src="pics/AC.png" class="img-thumbnail" alt="Card1"><img class="card2" src="pics/KC.png" class="img-thumbnail" alt="Card2"><img class="card3" src="pics/QC.png" class="img-thumbnail" alt="Card3"><img class="card4" src="pics/JC.png" class="img-thumbnail" alt="Card4"><img class="card5" src="pics/9C.png" class="img-thumbnail" alt="Card5">';
    
        document.write(message);
}

else {
    message = '<div class="panel-body">Player 1</div><div class="panel-score">21</div><img class="card1" src="pics/AC.png" class="img-thumbnail" alt="Card1"><img class="card2" src="pics/KC.png" class="img-thumbnail" alt="Card2">';
    document.write(message);
}
var players = []
var playerID;
var current_player;
var your_index;
var playing = false;

window.onload=player_join(playing);

function parse(str) {
    var args = [].slice.call(arguments, 1),
        i = 0;

    return str.replace(/%s/g, function() {
        return args[i++];
    });
}

function Get(yourUrl){
    var Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("GET", yourUrl, false);
    Httpreq.send(null);
    return Httpreq.responseText;          
}

function player_hit() {
    alert("player hit");
    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/HIT", playerID);

    var json_obj = JSON.parse(Get(uri));
    json_obj = JSON.parse(Get("http://game21.mybluemix.net/blackjack/v1.0/1/UPDATE"));

    var num_of_players = json_obj['table'].players.length;
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].playerID == playerID) {
            your_index = i;
        }
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = json_obj['table'].players[i].playerId;
            break;
        }
    }
    for(i = 0; i < json_obj['table'].players.length - 1; i++) {
        players[i].handValue = json_obj['table'].players[i].handValue;
        players[i].money = json_obj['table'].players[i].money;
        players[i].status = json_obj['table'].players[i].status;
        players[i].checkedIn = json_obj['table'].players[i].checkedIn;
        players[i].bet = json_obj['table'].players[i].bet;
        my_hand = json_obj['table'].players[i].hand;
        players[i].hand = [];
        for(a = 0; a < my_hand.length; a++) {
            players[i].hand.push(my_hand[a].name);
        }
    }
}

function player_stay() {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/STAY", playerID);
    var json_obj = JSON.parse(Get(uri));
    json_obj = JSON.parse(Get("http://game21.mybluemix.net/blackjack/v1.0/1/UPDATE"));
    var num_of_players = json_obj['table'].players.length;
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].playerID == playerID) {
            your_index = i;
        }
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = json_obj['table'].players[i].playerID;
            break;
        }
    }
    for(i = 0; i < json_obj['table'].players.length - 1; i++) {
        players[i].handValue = json_obj['table'].players[i].handValue;
        players[i].money = json_obj['table'].players[i].money;
        players[i].status = json_obj['table'].players[i].status;
        players[i].checkedIn = json_obj['table'].players[i].checkedIn;
        players[i].bet = json_obj['table'].players[i].bet;
        my_hand = json_obj['table'].players[i].hand;
        players[i].hand = [];
        for(a = 0; a < my_hand.length; a++) {
            players[i].hand.push(my_hand[a].name);
        }
    }
}

function player_bet(bet) {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/BET/", playerID)
    uri.concat(bet)
    var json_obj = JSON.parse(Get(uri));
    json_obj = JSON.parse(Get("http://game21.mybluemix.net/blackjack/v1.0/1/UPDATE"));
    var num_of_players = json_obj['table'].players.length;
    // var num_of_cards = json_obj['table'].players['playerID'].hand.length;
    // var new_card = json_obj['table'].players['playerID'].hand[num_of_cards - 1];
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].playerID == playerID) {
            your_index = i;
        }
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = json_obj['table'].players[i].playerID;
            break;
        }
    }
    for(i = 0; i < json_obj['table'].players.length - 1; i++) {
        players[i].handValue = json_obj['table'].players[i].handValue;
        players[i].money = json_obj['table'].players[i].money;
        players[i].status = json_obj['table'].players[i].status;
        players[i].checkedIn = json_obj['table'].players[i].checkedIn;
        players[i].bet = json_obj['table'].players[i].bet;
        my_hand = json_obj['table'].players[i].hand;
        players[i].hand = [];
        for(a = 0; a < my_hand.length; a++) {
            players[i].hand.push(my_hand[a].name);
        }
    }
}

function player_join(playing) {
    if(!playing) {
        playing = true;
        var uri = 'http://game21.mybluemix.net/blackjack/v1.0/join';
        var json_obj = JSON.parse(Get(uri));
        playerID = json_obj['playerID'];

        var num_of_players = json_obj['table'].players.length;
        alert(num_of_players);
        
        i = json_obj['table'].players.length - 1;
        player = {
                playerID:json_obj['table'].players[i].playerID,
                handValue:json_obj['table'].players[i].handValue,
                money:json_obj['table'].players[i].money,
                hand:[],
                status:json_obj['table'].players[i].status,
                checkedIn:json_obj['table'].players[i].checkedIn,
                bet:json_obj['table'].players[i].bet
            }
        my_hand = json_obj['table'].players[num_of_players - 1].hand;
        player.hand = [];
        for(i = 0; i < my_hand.length; i++) {
            player.hand.push(my_hand[i].name);
        }
        players.push(player);
        alert(players.length);
        for(i = 0; i < json_obj['table'].players.length; i++) {
            // alert(i);
            player = {
                playerID:json_obj['table'].players[i].playerID,
                handValue:json_obj['table'].players[i].handValue,
                money:json_obj['table'].players[i].money,
                hand:[],
                status:json_obj['table'].players[i].status,
                checkedIn:json_obj['table'].players[i].checkedIn,
                bet:json_obj['table'].players[i].bet
            }
            my_hand = json_obj['table'].players[num_of_players - 1].hand;
            player.hand = [];
            for(i = 0; i < my_hand.length; i++) {
                player.hand.push(my_hand[i].name);
            }
            players.push(player);
        }
        alert(playerID);
    }
}

function player_leave() {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/leave", playerID);
    Get(uri);
}


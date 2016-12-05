var player = {
    playerID:null,
    Score:null,
    bet:null,
    hand:[],
    status:null
    num_of_cards:null
}
var current_player;
var your_index;

window.onload=player_join();

function parse(str) {
    var args = [].slice.call(arguments, 1),
        i = 0;

    return str.replace(/%s/g, function() {
        return args[i++];
    });
}

function Get(yourUrl){
    var Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("GET",yourUrl,false);
    Httpreq.send(null);
    return Httpreq.responseText;          
}


var player_hit = function() {

    var uri = parse(/blackjack/v1.0/%s/HIT, playerID);

    var json_obj = JSON.parse(Get(uri));
    var num_of_players = json_obj['table'].players.length;
    var num_of_cards = json_obj['table'].players['playerID'].hand.length;
    var new_card = json_obj['table'].players['playerID'].hand[num_of_cards - 1];
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].playerID == player.playerID) {
            your_index = i;
        }
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = json_obj['table'].players[i].playerID;
            break;
        }
    }
    my_hand = json_obj['table'].players['playerID'].hand
    for(i = 0, i < my_hand.length; i++) {
        player.hand.push(my_hand[i].name);
    }
    player.num_of_cards = player.num_of_cards
    player.bet = json_obj['table'].players[your_index].bet
    player.score = json_obj['table'].players[your_index].score
    player.status = json_obj['table'].players[your_index].status
}

var player_stay = function() {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/STAY", playerID);

    var json_obj = JSON.parse(Get(uri));
    var num_of_players = json_obj['table'].players.length;
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].playerID == player.playerID) {
            your_index = i;
        }
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = json_obj['table'].players[i].playerID;
            break;
        }
    }
    my_hand = json_obj['table'].players['playerID'].hand
    player.hand = []
    for(i = 0, i < my_hand.length; i++) {
        player.hand.push(my_hand[i].name);
    }
    player.num_of_cards = player.num_of_cards
    player.bet = json_obj['table'].players[your_index].bet
    player.score = json_obj['table'].players[your_index].score
    player.status = json_obj['table'].players[your_index].status
}

var player_bet = function(bet) {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/BET/", playerID)
    uri.concat(bet)

    var json_obj = JSON.parse(Get(uri));
    var num_of_players = json_obj['table'].players.length;
    // var num_of_cards = json_obj['table'].players['playerID'].hand.length;
    // var new_card = json_obj['table'].players['playerID'].hand[num_of_cards - 1];
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].playerID == player.playerID) {
            your_index = i;
        }
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = json_obj['table'].players[i].playerID;
            break;
        }
    }
    my_hand = json_obj['table'].players['playerID'].hand
    player.hand = []
    for(i = 0, i < my_hand.length; i++) {
        player.hand.push(my_hand[i].name);
    }
    player.num_of_cards = player.num_of_cards
    player.bet = json_obj['table'].players[your_index].bet
    player.score = json_obj['table'].players[your_index].score
    player.status = json_obj['table'].players[your_index].status
}

function player_join() {

    var uri = 'http://game21.mybluemix.net/blackjack/v1.0/join'
    player.playerID = 1;
    alert(player.playerID);
    var json_obj = JSON.parse(Get(uri));
    playerID = json_obj['player'].playerID;
}



var player_leave = function() {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/leave", playerID);
    Get(uri);
}


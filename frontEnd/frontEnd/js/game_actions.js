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

var player_hit = function(playerID) {

    var uri = parse(/blackjack/v1.0/%s/HIT, playerID);

    var json_obj = JSON.parse(Get(uri));
    var num_of_players = json_obj['table'].players.length;
    var num_of_cards = json_obj['table'].players['playerID'].hand.length;
    var new_card = json_obj['table'].players['playerID'].hand[num_of_cards - 1];
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = i + 1;
            break;
        }
    }
    return {
        new_card: new_card,
        current_player: current_player
    };
}

var player_stay = function(playerID) {

    var uri = parse(/blackjack/v1.0/%s/STAY, playerID);

    var json_obj = JSON.parse(Get(uri));
    var num_of_players = json_obj['table'].players.length;
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = i + 1;
            break;
        }
    }
    return {
        current_player: current_player
    };
}

var player_bet = function(playerID, bet) {

    var uri = parse(/blackjack/v1.0/%s/BET/, playerID)
    uri.concat(bet)

    var json_obj = JSON.parse(Get(uri));
    var num_of_players = json_obj['table'].players.length;
    // var num_of_cards = json_obj['table'].players['playerID'].hand.length;
    // var new_card = json_obj['table'].players['playerID'].hand[num_of_cards - 1];
    var current_player = null;
    for(i = 0; i < num_of_players; i++) {
        if(json_obj['table'].players[i].status == "WAITING_ON_ACTION") {
            current_player = i;
            break;
        }
    }
    return {
        current_player: current_player
    };
}



// function player_stay(playerID) {

//     var uri = parse(/blackjack/v1.0/%s/STAY, playerID)
//     // console.log(info);
//     // 

//     $.post({
//         url: uri,
//         success: function(data) {
//             //what happens when call succeeds
//         },
//         error: function(data) {
//             alert("something went wrong");
//         }
//     });
// }

// function player_stay(playerID, bet) {


//     var uri = parse(/blackjack/v1.0/%s/bet/, playerID)
//     uri.concat(bet)

//     $.post({
//         url: uri,
//         success: function(data) {
//             //what happens when call succeeds
//         },
//         error: function(data) {
//             alert("something went wrong");
//         }
//     });
// }
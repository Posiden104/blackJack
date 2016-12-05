// alert(localStorage[players]);

function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function eraseCookie(name) {
    createCookie(name,"",-1);
}

var players = [];
var json_obj = JSON.parse(Get("http://game21.mybluemix.net/blackjack/v1.0/1/UPDATE"));

var dealer_hand = json_obj['table'].dealerHand;
var dealerhand = [];
for(a = 0; a < dealer_hand.length; a++) {
    dealerhand.push(dealer_hand[a].name);
}
// alert(dealer_hand.length)

var num_of_players = json_obj['table'].players.length;
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
    my_hand = json_obj['table'].players[i].hand;
    player.hand = [];
    for(a = 0; a < my_hand.length; a++) {
        player.hand.push(my_hand[a].name);
    }
    players.push(player);
}
alert(players.length);

var playerID;
var current_player;
var your_index;
var playing = false;
var num_players;
// sessionStorage.setItem("playing", "false");
// document.cookie = "username = true;";
// var ca = document.cookie;
// alert(ca)
// window.onload=player_join();




// alert("this is readcookies = " + readCookie('playing'));
// eraseCookie('playing');
if(readCookie('playing') == null || readCookie('playing') != 'true') {
    createCookie('playing', 'false', 10);
}
// alert("reading cookie " + readCookie('playing'));

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
    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/HIT", readCookie('playerID'));

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
    location.reload();
}

function player_stay() {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/STAND", readCookie('playerID'));
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
    location.reload();
}

function player_bet(bet) {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/BET/", readCookie('playerID'))
    uri = uri + bet;
    alert(uri);
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
    location.reload();
}

function player_join() {
    // var x = sessionStorage.getItem("playing");
    // var x = readCookie('playing'); 
    // alert(x);
    // if(readCookie('playing') == 'false') {
        // eraseCookie('playing');
        // createCookie('playing', 'true');
        // sessionStorage.setItem("playing", "true");
        alert("player joined");
        var uri = 'http://game21.mybluemix.net/blackjack/v1.0/join';
        var json_obj = JSON.parse(Get(uri));
        playerID = json_obj['playerID'];
        createCookie('playerID', playerID);
        var num_of_players = json_obj['table'].players.length;
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
            my_hand = json_obj['table'].players[i].hand;
            player.hand = [];
            for(a = 0; a < my_hand.length; a++) {
                player.hand.push(my_hand[a].name);
            }
            players.push(player);
        }
        alert(playerID);
        alert(players.length);
        // document.getElementById("p1").innerHTML = "New text!";
        // document.getElementById("p2").innerHTML = "New text!";
        // document.getElementById("p3").innerHTML = "New text!";
        // document.getElementById("p4").innerHTML = "New text!";
        // document.getElementById("p5").innerHTML = "New text!";
        // document.getElementById("p6").innerHTML = "New text!";
        location.reload();

    // }
}

function player_leave() {

    var uri = parse("http://game21.mybluemix.net/blackjack/v1.0/%s/leave", readCookie('playerID'));
    Get(uri);
}


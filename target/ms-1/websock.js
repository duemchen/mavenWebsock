
var ws = new WebSocket("ws://" + document.location.host + document.location.pathname + "endpoint");

// ProjektID
var pid = -1;
var ptext = "";
//
//**Websock Events**************************************************************
ws.onopen = function (event) {
    onOpen(event);
};
ws.onmessage = function (evtent) {
    onMessage(evtent);
};
ws.onclose = function ()
{
    $('#topic').val('disconnected');
};
//******************************************************************************

function onOpen(event) {
    $('#topic').val('connected');
}

function onMessage(event) {
    var mg = event.data;
    $('#topic').val(mg);
    var msg = JSON.parse(event.data);
    /*
     * es kommen verschiedene messages
     * status 
     * 
     */

    switch (msg.type) {
        case "status":
            /*
             * den aktuellen Erfassungsstatus aus den letzten Buchungen darstellen
             msg.zeit
             msg.auftrag             
             * die Knöpfe steuern: Wenn Kommen dann Gehen und umgekehrt. Nur bei Kommen darf Auftrag ,
             */
            break;
        case "projekte":
            /*
             * gemäss der geoposition die Liste laden
             * bzw. wenn der Client auswählt "alle Projekte", dann auch alle
             */
            break;
    }

    o = JSON.parse('{"x":"abc1","age":51}');
    //   $('#topic').val(o.age);
    //alert("Message received:" + msg);
}

function sendMessage(msg) {
    ws.send("Message from client: " + msg);
    if (typeof (Storage) !== "undefined") {
        console.log('storage ok')
        // localStorage.setItem("lastname", "Smith");
        console.log('name', localStorage.getItem("lastname"));

    } else {
        console.log('storage no')
    }
}



$(document).ready(function () {
    $("#kommen").click(function () {
        $('#topic').val('kommen gebucht');
        var msg = {
            type: 'zeitbuchung',
            text: 'KOMMEN',
            buchart: 'KO',
            date: Date.now()
        };
        sendMessage(JSON.stringify(msg));
    });
    $("#gehen").click(function () {
        $('#topic').val('gehen gebucht');
        var msg = {
            type: 'zeitbuchung',
            text: 'GEHEN',
            buchart: 'GE',
            date: Date.now()
        };
        sendMessage(JSON.stringify(msg));
    });
    $("#aanfang").click(function () {
        $('#topic').val('Auftrag gebucht');
        $(':mobile-pagecontainer').pagecontainer('change', '#page2');
    });
    $("#aende").click(function () {
        $("#aende")
        $("#aende").attr('disabled', 'disabled');
        $('#topic').val('AuftEnde gebucht');
        var msg = {
            type: 'auftragsbuchung',
            text: 'AUFTRAGENDE',
            buchart: 'AE',
            date: Date.now()
        };
        sendMessage(JSON.stringify(msg));
    });
    $("#lback").click(function () {
        $('#topic').val('AK');
        $(':mobile-pagecontainer').pagecontainer('change', '#page1');
    });

    $('#listview').on('click', 'li', function () {
        console.log($(this).attr('id'));
        //  alert("Works"); // id of clicked li by directly accessing DOMElement property
        // alert($(this).attr('id'));
    });

    $(this).on("click", "li", function () {
        console.log($(this).text(), $(this).attr('id'));
        pid = $(this).attr('id');
        ptext = $(this).text();
        $('#projekt').val($(this).text());
        $(':mobile-pagecontainer').pagecontainer('change', '#page3');
    });

    $("#pback").click(function () {
        $(':mobile-pagecontainer').pagecontainer('change', '#page2');
    });
    $("#pok").click(function () {
        var msg = {
            type: "auftragsbuchung",
            buchart: "AE",
            text: ptext,
            pid: pid,
            date: Date.now()
        };
        sendMessage(JSON.stringify(msg));
        $(':mobile-pagecontainer').pagecontainer('change', '#page1');
    });


});


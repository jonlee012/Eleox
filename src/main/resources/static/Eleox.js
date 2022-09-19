
//username and password validation
function user1() {
    var user = document.getElementById('username').value
    var pass = document.getElementById('password').value
    if (user == "interview" && pass == "eleox") {
        alert('correct login credentials!')
        window.open("interview-page.html")
    } else {
        alert('incorrect username or password')
        window.location.reload();
    }
}

//authorization for username and password
// var clientId = "MyApp";
// var clientSecret = "MySecret";

// var authorizationBasic = $.base64.btoa(clientId + ':' + clientSecret);
// var authorizationBasic = window.btoa(clientId + ':' + clientSecret);

$.ajax({
    type: 'POST',
    url: oAuth.AuthorizationServer,
    data: { username: 'interview', password: 'eleox', grant_type: 'password' },
    dataType: "json",
    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
    xhrFields: {
        withCredentials: true
    },
    // crossDomain: true,
    headers: {
        "Authorization": "BearereyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJVbWJyYWdlIiwiaWF0IjoxNjA5NDM1Njc1LCJleHAiOjI1NTYxMjA0ODYsImF2ZCI6Ind3dy51bWJyYWdlLmNvbSIsInN1YiI6IndhbHQua2lkbmV7QHVtYnJhZ2UuY29tIiwiZmlyc3RuYW1lIjoiV2FsdCIsImxhc3RuYW1lIjoiS2lkbmV5IiyiZW1haWwiOiJ3YWx0LmtpZG5leUB1bWJyYWdlLmNvbSIsImpvYlRpdGxlIjliQ3JhZnQ6IFNvZnR3YXJlIEVuZ2luZWVyaW5nIn0.1h55DREei28Jx6LyWSkZ46cjBA5IsHJb3nT-7MIKsSA"
    },
    //beforeSend: function (xhr) {
    //},
    success: function (result) {
        var token = result;
    },
    //complete: function (jqXHR, textStatus) {
    //},
    error: function (req, status, error) {
        alert(error);
    }
});
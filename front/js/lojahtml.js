function login() {
    user = {
        "email": document.getElementById("staticEmail").value,
        "senha": document.getElementById("inputPassword").value
    }


    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Cookie", "JSESSIONID=4492DCD57C5361903BDDAA3CE4B0117D");

    var raw = JSON.stringify({
        "email": "inicial@gft.com",
        "senha": 123
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow',
        mode: "no-cors"
    };

    fetch("http://localhost:9094/login", requestOptions)
    .then(function(res) {
        return res.text();
      }).then(function(body) {
       console.log(body);
       // NEW CODE
       // body.access_token is my guess, you might have to change this
       // based on the response
       window.sessionStorage.setItem('access_token', body.access_token)
      });
}

/*
fetch('http://localhost:6000/api/v1.0/posts/1', {
    method: 'GET',
    headers: {
        'Authorization': 'Bearer ' + _token; // 
    }
})
.then(function(response) {
    console.log(response);
}).catch(function (response) {
    console.log(response);
});
*/
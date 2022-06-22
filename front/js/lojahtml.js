atualizaLogado();

function atualizaLogado() {
    if (window.sessionStorage.getItem('access_token')) {
        console.log(window.sessionStorage.getItem('access_token'));
        document.getElementById("usuarioLogadoInfo").innerHTML = "Usuário Logado";
    } else {
        document.getElementById("usuarioLogadoInfo").innerHTML = "Usuário NÃO Logado";
    }
}

function login() {
    user = {
        "email": document.getElementById("staticEmail").value,
        "senha": document.getElementById("inputPassword").value
    }


    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

     var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(user),
        redirect: 'follow'
    };

    fetch("http://localhost:9094/login", requestOptions)
        .then(response =>
            {
                if (response.ok) {
                    return response.json();
                } else {
                    document.getElementById("usuarioLogado").innerHTML = "Acesso negado";
                    alert("Acesso negado");
                    window.location.href="login.html";
                }
            }
        )
        .then(json => {
            if (json.token) {
                alert("Acesso autorizado: \n" + json.token);
                window.sessionStorage.setItem('access_token', json.token);
                window.location.href="lojakotlin.html";
            } 
        })
        .catch(erro => window.sessionStorage.setItem('access_token', "")
    );

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
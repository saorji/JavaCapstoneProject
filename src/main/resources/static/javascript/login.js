var jwt = localStorage.getItem("jwt");
var roles = localStorage.getItem("roles");
if (jwt != null && roles == 'ROLE_PATIENT') {
    window.location.href = './patient.html'
}


function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/api/auth/signin");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "username": username,
        "password": password
    }));
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            const objects = JSON.parse(this.responseText);
            console.log(objects);
            if (this.status == 200 && objects['roles'] == 'ROLE_DOCTOR') {
                localStorage.setItem("roles", objects['roles']);
                localStorage.setItem("jwt", objects['accessToken']);
                Swal.fire({
                    text: objects['message'],
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = './doctor.html';
                    }
                });
            } else if (this.status == 200 && objects['roles'] == 'ROLE_PATIENT') {
                localStorage.setItem("jwt", objects['accessToken']);
                Swal.fire({
                    text: objects['message'],
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = './patient.html';
                    }
                });
            } else {
                Swal.fire({
                    text: objects['message'],
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        }
    };
    return false;
}

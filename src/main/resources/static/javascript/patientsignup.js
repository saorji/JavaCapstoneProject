
function patientregister() {
    Swal.fire({
        title: 'register',
        html:

            '<input id="signupusername" class="swal2-input" placeholder="username Here">' +
            '<input id="firstName" class="swal2-input" placeholder="fistname">' +
            '<input id="lastName" class="swal2-input" placeholder="lastname">' +
            '<input type="email" id="signupemail" class="swal2-input" placeholder="email">' +
            '<input type="password" id="password" class="swal2-input" placeholder="password">' ,


        focusConfirm: false,


        preConfirm: () => {



            patientuserCreate();
        }

    })
}


function patientuserCreate() {
    const signupusername = document.getElementById("signupusername").value;
    const signupemail = document.getElementById("signupemail").value;
    const password = document.getElementById("password").value;
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/api/auth/patient/signup");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "signupusername": signupusername,
        "signupemail": signupemail,
        "password": password,
        "firstName": firstName,
        "lastName": lastName,

    }));
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            const objects = JSON.parse(this.responseText);
            console.log(objects);
            if (this.status == 200) {

                Swal.fire({
                    text: objects['message'],
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = 'login.html';
                    }
                });
            } else {
                Swal.fire({
                    text: objects['message'],
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        };
        // }
    }
}


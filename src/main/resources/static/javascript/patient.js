var jwt = localStorage.getItem("jwt");
var userId = localStorage.getItem("userid");
if (jwt == null) {
    window.location.href = './login.html'
}

function loadUser() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/api/auth/user");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.setRequestHeader("Authorization", "Bearer "+jwt);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            const objects = JSON.parse(this.responseText);
            if (objects["message"] == "ok") {
                // const user = objects["user"]
                document.getElementById("fname").innerHTML = objects['email'];
                document.getElementById("avatar").src = user["avatar"];
                document.getElementById("username").innerHTML = objects['username'];
                localStorage.setItem("userid", objects['id']);

            }
        }
    };
}

loadUser();


function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/api/appointment/appointments");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var trHTML = '';
            const objects = JSON.parse(this.responseText);
            for (let object of objects) {
                trHTML += '<tr>';
                trHTML += '<td>'+object['id']+'</td>';
                trHTML += '<td>'+object['title']+'</td>';
                trHTML += '<td>'+object['description']+'</td>';
                trHTML += '<td>'+object['status']+'</td>'
                trHTML += '<td>'+object['creationDate']+'</td>';
                if(object['diagnosis'] != null) {
                    trHTML += '<td><button type="button" class="btn btn-outline-secondary" onclick="showUserEditBox(' + object['id'] + ')">View Diagnosis Result</button>';
                }else{
                    trHTML += '<td><button type="button" class="btn btn-outline-secondary">Diagnosis Not CarryOut Yet</button>';
                }
                // trHTML += '<button type="button" class="btn btn-outline-danger" onclick="userDelete('+object['id']+')">Del</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("mytable").innerHTML = trHTML;
        }
    };
}

loadTable();

function showAppointmentCreateBox() {
    Swal.fire({
        title: 'Book A New Appointment with your Doctor',
        html:
            '<input id="iD" type="hidden">' +
            '<input id="title" class="swal2-input" required placeholder="title">' +
            '<input id="description" class="swal2-input" required placeholder="Appointment Description">' ,

        focusConfirm: false,


        preConfirm: () => {



            appointmentCreate();
        }

    })
}


function appointmentCreate() {
    const description = document.getElementById("description").value;
    const title = document.getElementById("title").value;
    const userId = localStorage.getItem("userId");

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/api/appointment/create");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
      "description":description, "title": title,
        // "avatar": "https://www.mecallapi.com/users/cat.png"
    }));
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            const objects = JSON.parse(this.responseText);
            console.log(objects);

            if ( this.status == 200) {
                const objects = JSON.parse(this.responseText);
                Swal.fire(objects['message']);
                loadTable();
            } else {
                Swal.fire({
                    text: objects['message'],
                    icon: 'error',
                    confirmButtonText: 'Field Can Not Be Blank'
                });
            };
        }
    }
}


function showUserEditBox(id) {
    console.log(id);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/api/diagnosis/"+id);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            const objects = JSON.parse(this.responseText);
            // const user = objects['user'];
            // console.log(user);
            Swal.fire({
                title: 'Diagnosis Result',
                html:
                    // '<input id="id" type="hidden" value='+user['id']+'>' +
                    '<input id="treatmentType" class="swal2-input" placeholder="TreatmentType" readonly value="'+objects['diagnosistreatmentType']+'">' +
                    '<input id="drugDescription" class="swal2-input" placeholder="DrugDescription" readonly value="'+objects['diagnosisdrugDescription']+'">' ,
                    // '<input id="username" class="swal2-input" placeholder="Username" value="'+user['username']+'">' +
                    // '<input id="email" class="swal2-input" placeholder="Email" value="'+user['email']+'">',
                focusConfirm: false,
                preConfirm: () => {
                    userEdit();
                }
            })
        }
    };
}

function userEdit() {
    // const id = document.getElementById("id").value;
    const treatmentType = document.getElementById("treatmentType").value;
    const drugDescription = document.getElementById("drugDescription").value;
    // const username = document.getElementById("username").value;
    // const email = document.getElementById("email").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/api/appointment/appointments");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "treatmentType": treatmentType, "drugDescription": drugDescription,

    }));
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            const objects = JSON.parse(this.responseText);
            Swal.fire(objects['message']);
            loadTable();
        }
    };
}



function userDelete(id) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "https://www.mecallapi.com/api/users/delete");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": id
    }));
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            const objects = JSON.parse(this.responseText);
            Swal.fire(objects['message']);
            loadTable();
        }
    };
}


function logout() {
    localStorage.removeItem("jwt");
    window.location.href = './login.html'
}
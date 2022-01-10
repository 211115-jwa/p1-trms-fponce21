hiThereYall()
let mainDiv = document.getElementById('info');
function hiThereYall(){
    if(!loggedInPerson){
        openLogin();
    }else if(loggedInPerson.role.roleId > 1) {
        mainDiv.innerHTML += `<p>Hello Supervisor ${loggedInPerson.firstName} ${loggedInPerson.lastName}.  From here you can look at all Reimbursement Requests.  Do you have Requests to approve/deny?  Would you like to submit a request?  Look at your submitted requests.</p>`
    }else {
        mainDiv.innerHTML += `<p>Hello Employee ${loggedInPerson.firstName} ${loggedInPerson.lastName}.  From here you can look at all Reimbursement Requests.  Would you like to submit a request?  Look at your submitted requests.</p>`
    }
    greetings()
    let mainDiv = document.getElementById('info');
    function greetings(){
        if(!loggedInPerson){
            openLogin();
        }else if(loggedInPerson.role.roleId > 1) {
            mainDiv.innerHTML += `<p>Welcome, Supervisor ${loggedInPerson.firstName} ${loggedInPerson.lastName}.  From here you can look at all Reimbursement Requests.  Do you have Requests to approve/deny?  Would you like to submit a request?  Look at your submitted requests.</p>`
        }else {
            mainDiv.innerHTML += `<p>Welcome, Employee ${loggedInPerson.firstName} ${loggedInPerson.lastName}.  From here you can look at all Reimbursement Requests.  Would you like to submit a request?  Look at your submitted requests.</p>`
        }
    }
}
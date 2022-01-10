welcomeToTRMS()
let mainDiv = document.getElementById('info');
function welcomeToTRMS(){
    if(!loggedInPerson){
        openLogin();
    }else if(loggedInPerson.role.roleId > 1) {
        mainDiv.innerHTML += `<p>Greetings Supervisor ${loggedInPerson.firstName} ${loggedInPerson.lastName}.  You can view all Reimbursement Requests from here.  Do you have any Requests to approve/deny?  Would you like to submit a request?  Look at submitted requests.</p>`
    }else {
        mainDiv.innerHTML += `<p>Greetings Employee ${loggedInPerson.firstName} ${loggedInPerson.lastName}.  You can view all Reimbursement Requests from here.  Would you like to submit a request?  Look at submitted requests.</p>`
    }
}
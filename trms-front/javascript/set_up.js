"use strict";

let appUrl = 'http://localhost:8080/';
let loggedInPerson;
checkLogin().then(setupNav);

async function checkLogin() {
    let empId = localStorage.getItem('Token');
    if (empId) {
        let response = await fetch(appUrl + 'employees/' + empId + '/auth');
        if (response.status === 200) {
            loggedInPerson = await response.json();
        }
    }
}
function setupNav() {
    let nav = document.getElementById('nav');

    if (!loggedInPerson) {
        nav.innerHTML = `<span id="navLeft">
        <a hidden>My Submitted Requests</a>
        <a href="index.html">TRMS</a>
        <a hidden>My Submitted Requests</a>
        </span>
        <span id="navRight">
        <button id="login">Log In</button>
        </span>`;

        document.getElementById('login').addEventListener('click',openLogin);
    } else if (loggedInPerson.role.name !== 'Supervisor') {
        nav.innerHTML = `<span id="navLeft">
        <a href="addreqs.html">Add Request</a>
        <a href="index.html">TRMS</a>
        <a href="myrequests.html">Submitted Requests</a>
        </span>
        <span id="navRight">
        <a id="manageEmployee" href="manage.html">${loggedInPerson.username}</a>
        <button id="logout">Log Out</button>
        </span>`;

        document.getElementById('logout').addEventListener('click',logOut);
    } else {
        nav.innerHTML = `<span id="navLeft">
        <a href="addreqs.html">Add Request</a>
        <a href="index.html">TRMS</a>
        <a href="myrequests.html">Submitted Requests</a>
        <a href="approvereqs.html">Requests to Approve</a>
        </span>
        <span id="navRight">
        <a id="manageEmployee" href="manage.html">${loggedInPerson.username}</a>
        <button id="logout">Log Out</button>
        </span>`;

        document.getElementById('logout').addEventListener('click',logOut);
    }

    closeLogin();
}

function openLogin() {
    let loginPane = document.createElement('section');
    loginPane.id = 'loginPane';
    loginPane.innerHTML = `
        <form class="loginForm" id="loginForm">
            <h3>Log In</h3>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username">
            &nbsp;&nbsp;&nbsp;
            <label for="password">Password:</label>
            <input type="password" id="password" name="password">
            <button id="loginBtn" type="button">Submit</button>
        </form>
    `;
    document.getElementsByTagName('main')[0].insertAdjacentElement("beforebegin",loginPane);

    document.getElementById('loginBtn').addEventListener('click', submitLogin);

   //document.getElementById('login').removeEventListener('click',openLogin);
    //document.getElementById('login').addEventListener('click',closeLogin);
}

function closeLogin() {
    if (document.getElementById('loginForm')) {
        document.getElementById('loginForm').remove();
    }
    if (document.getElementById('login')) {
        document.getElementById('login').addEventListener('click',openLogin);
    }
}

async function submitLogin() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let creds = {
        'username':username,
        'password':password
    };

    let response = await fetch(appUrl + 'employees/auth',{method:'POST',body:JSON.stringify(creds)});
    if (response.status===200) {
        let token = await response.text();
        localStorage.setItem('Token', token);
        checkLogin().then(setupNav);
    } else if (response.status===404) {
        // TODO
        let msg = await response.text();
        alert(msg);
    }
}

function logOut() {
    localStorage.removeItem('Token');
    loggedInPerson=null;
    checkLogin().then(setupNav);
}
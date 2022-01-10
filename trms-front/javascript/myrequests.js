getMySubmittedReqs();

async function getMySubmittedReqs() {
    let empId = localStorage.getItem('Token');
    let response = await fetch(appUrl + 'requests/requestor/' + empId); 
    let response1 = await fetch(appUrl + 'employees/' );       
    if (response.status === 200 && response1.status === 200) {
        let reqs = await response.json();
        let emps = await response1.json();
        showReqs(reqs, emps);
    }
}


function showReqs(reqs, emps) {
    document.getElementById('individualsReqs') 
    
        let requestsTable = document.getElementById('individualsReqs');
      let i = 0;
   
    for (let req of reqs) 
    {
        let status = (reqs[i].status);
        stat = (status.statusId + ': ' + status.name);
        let eventType = (reqs[i].eventType);
        eType = (eventType.eventId + ': ' + eventType.name);
        let gradingFormat = (reqs[i].gradingFormat);
        gFormat = (gradingFormat.formatId + ': ' + gradingFormat.name);
        let day = (req.eventDate.dayOfMonth);
        let month = (req.eventDate.monthValue);
        let year = (req.eventDate.year);
        let combDate = (month + '/' + day + '/' + year);
        let hour = (req.eventTime.hour);
        let minute = (req.eventTime.minute);
        let second = (req.eventTime.second);
        let combTime = (hour + ':' + minute + ':' + second);
        let subDay = (req.submittedAt.dayOfMonth);
        let subMonth = (req.submittedAt.monthValue);
        let subYear = (req.submittedAt.year);
        let subHour = (req.submittedAt.hour);
        let subMinute = (req.submittedAt.minute);
        let sub = (subMonth + '/' + subDay + '/' + subYear + ' ' + subHour + ':' + subMinute);
        let eId = (reqs[i].requestor.empId);
        let fName;
        let lName;
        for (let emp of emps) {
            if (eId == (emp.empId)){
                fName = (emp.firstName);
                lName = (emp.lastName);
            }
        }
        let employee = (fName + " " + lName)
        let rowForReqs = document.createElement('tr');
        rowForReqs.setAttribute("id","req_row")
        for (let field in req) {
             if (field == 'requestor'){
            let column = document.createElement('td');
            column.innerText = eId;
            rowForReqs.appendChild(column);
            let column1 = document.createElement('td');
            column1.innerText = (employee);
            rowForReqs.appendChild(column1);
             }else if(field == 'eventDate'){ 
                let column = document.createElement('td');
                column.innerText = (combDate);
                rowForReqs.appendChild(column);
            }else if(field == 'eventTime'){ 
                let column = document.createElement('td');
                column.innerText = (combTime);
                rowForReqs.appendChild(column);
             }else if(field == 'gradingFormat'){ 
                let column = document.createElement('td');
                column.innerText = (gFormat);
                rowForReqs.appendChild(column);
            }else if(field == 'eventType'){ 
                let column = document.createElement('td');
                column.innerText = (eType);
                rowForReqs.appendChild(column);
            }else if(field == 'status'){ 
                let column = document.createElement('td');
                column.innerText = (stat);
                rowForReqs.appendChild(column);
            }else if(field == 'submittedAt'){ 
                let column = document.createElement('td');
                column.innerText = (sub);
                rowForReqs.appendChild(column);
            }else{
                let column = document.createElement('td');
                column.innerText = req[field];
                rowForReqs.appendChild(column);
             }
           
        }
        i++;
            requestsTable.appendChild(rowForReqs);
         
    }
}
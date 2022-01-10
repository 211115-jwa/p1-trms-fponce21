document.getElementById(`submitbutton`).onclick = submitRequest;

async function submitRequest() {

        
    let etp = document.getElementById("eventtype").value;
    let et = {
        "eventId": etp
    }
    let st = {
        "statusId": 1,
        "name": "Pending Approval",
        "approver": "Supervisor"
    };
    let gfid =  document.getElementById("gformat").value;
    let gf  = {
        "formatId":  gfid
    }
                
    let edate = document.getElementById("eventdate").value;
    let etime = String(document.getElementById("etime").value);
    etime = etime + ":01";
    let local = document.getElementById("location").value ;
    let desc = document.getElementById("descBox").value;
    let cost = document.getElementById("cost").value;
    
    let request = {
        "requestor": loggedInPerson,
        "eventDate": edate,
        "eventTime": etime,
        "location": local,
        "description": desc,
        "cost": cost,
        "gradingFormat": gf,
        "eventType": et,
        "status":st,
        "submittedAt": ''
    };
    console.log(request);
    if(request.cost == ""){
        alert("Please Enter Proper Form Data")
    }
    else{
        
      console.log(request);
        let response = await fetch(appUrl + 'reqs', {
        method: 'POST',
        body: JSON.stringify(request),
        });
        if (response.status === 201) {
            let reqs = await response.json();
            console.log(reqs);
        }    
    }
}
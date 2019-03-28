(function () {
	window.onload = () => {
		getData();
		maintainCookie();
		if(username !== null && identity !== null) {
			const submitBtn = document.getElementById('submit');
			submitBtn.classList.remove('disabled');
			submitBtn.onclick = collectData;
		}
	};
})();

var username = null;
var identity = null;
function maintainCookie() {
    let cookie = [];
    cookie = document.cookie.split(";");
    for(let i of cookie) {
        const s1 = i.split("=")[0].trim();
        const s2 = i.split("=")[1].trim();
        if(s1 === 'username') {
            username = s2;
        } else if(s1 === 'identity') {
            identity = s2;
        }
    }
}

function ajaxRequest (url, data, type, submitedType) {
    return new Promise((resolve, reject) => {
        let req = new XMLHttpRequest();
        req.responseType = 'json';
        if(type === 'get') {
        	req.open('GET', url, true);
        } else if(type === 'post'){
        	req.open('POST', url, true);
        }
        req.withCredentials = true;
        if(submitedType === 'json') {
        	req.setRequestHeader('Content-Type', 'application/json');
        } else if(submitedType === 'form'){
    		req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    	}
        req.onload = () => {
            if(req.status === 200) {
                resolve(req.response);
            } else {
                reject(Error(req.statusText));
            }
        };
        req.onerror = () => {
            reject(Error('Network Error'));
        };
        if(data !== undefined && data !== '') {
            req.send(data);
        } else {
            req.send();
        }
    });
}

function getData () {
	const id = sessionStorage.getItem("pid");
	const data = 'id=' + id + '&name=true';
	const url = 'http://localhost:8080/project/single/?' + data;
	ajaxRequest(url, data, 'get', 'form').then(response => {
		//console.log(response);
		display(response.project);
	});
}

function display(p) {
	const body = document.getElementById('project');
	const title = document.getElementById('title');
	const description = document.getElementById('description');
	const reference = document.getElementById('reference');
	const degree = document.getElementById('degree');
	const supervisor = document.getElementById('supervisor');
	const language = document.getElementById('language');
	const level1 = document.getElementById('level1');
	const level2 = document.getElementById('level2');
	const level3 = document.getElementById('level3');
	
	title.innerHTML = 'Title: ' + p.title;
	degree.innerHTML = 'Required degree: ' + '<b>' + p.degree + '</b>';
	supervisor.innerHTML = 'Supervisor: ' + '<b>' + p.supervisor + '</b>';
	description.innerHTML = '<span>Description: </span><br>' + p.description;
	reference.innerHTML = '<span>Reference: </span><br>' + p.reference;
	language.innerHTML = 'Language: ' + '<b>' + p.language + '</b>';
	level1.innerHTML = 'Level1: ' + p.level1;
	level2.innerHTML = 'Level2: ' + p.level2;
	level3.innerHTML = 'Level3: ' + p.level3;
}

function collectData () {
	const selected = document.getElementById('tendency');
	const selectedInd = selected.selectedIndex;
	const marks = selected[selectedInd].innerHTML;
	console.log(marks);
	if(selectedInd === 0) {
		alert("Please choose your tendency marks");
		return false;
	}
	const pid = sessionStorage.getItem("pid");;
	const sid = username;
	//http://localhost:8080/project/interested?pid=1&sid=18000000&tendency=5
	const answer = confirm('Are you sure to designate this project to this student?');
	const data = '?pid=' + pid + '&sid=' + sid + '&tendency=' + marks;
	let url = 'http://localhost:8080/project/interested' + data;
	if(answer) {
		ajaxRequest(url, '', 'get', 'form').then(response => {
			if(response === 1) {
				alert('Submitted successfully');
			}
		});
	}
}
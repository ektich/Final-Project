(function (){
	//loadCategory();
    window.onload = () => {
        loadCategory();
        setTimeout(() => {
            test0();
        }, 2000);
    }
})();


let level1 = 0;
let level2 = 0;
function queryCCSCategory () {
    const level1Select = document.getElementById('ipLevel1');
    const level2Select = document.getElementById('ipLevel2');
    const level3Select = document.getElementById('ipLevel3');
    const index1 = level1Select.selectedIndex;
    const index2 = level2Select.selectedIndex;
    const index3 = level3Select.selectedIndex;
    let code1 = 0;
    let code2 = 0;
    let code3 = 0;
    if(index1 !== 0) {
        code1 = document.getElementById('ipLevel1').getElementsByTagName('option')[index1].getAttribute('data-id');
    }
    if(index2 !== 0) {
        code2 = document.getElementById('ipLevel2').getElementsByTagName('option')[index2].getAttribute('data-id');
    }
    if(index3 !== 0) {
        code3 = document.getElementById('ipLevel3').getElementsByTagName('option')[index3].getAttribute('data-id');
    }
    //console.log(level1 + '/ ' + level2 + '/ ' + level3);
    const first = document.createElement('option');
    first.innerHTML = 'Choose...';    
    if(code1 === 0) {
        //console.log(1);
        clearOptions(level2Select);
        clearOptions(level3Select);
        level1 = 0;
    } else if (code1 !== 0 && code2 === 0 && code3 === 0){
        //console.log(2);
        loadCategory(code1);
        level1 = code1; 
    } else if(code1 !== level1 && (code2 !== 0 || code3 !== 0)) {
        //console.log(3);
        clearOptions(level2Select);
        clearOptions(level3Select);
        loadCategory(code1);
        level1 = code1;
    } else if(code2 === 0 || code2 !== level2){
        //console.log(4);
        clearOptions(level3Select);
        loadCategory(code2);
        level2 = code2;
    }
}

function insertOption (i, select) {
    let option = document.createElement('option');
    option.innerHTML = i.name;
    option.setAttribute('data-id', i.id);
    select.appendChild(option);
}

function clearOptions(e) {
    const option = document.createElement('option');
    option.innerHTML = 'Choose...';
    e.innerHTML = '';
    e.appendChild(option);
}

function loadCategory (code) {
    code = code === undefined ? 0 : code;
    let url = 'http://localhost:8080/project/level?code=' + code;
    let level1Select = document.getElementById('ipLevel1');
    let level2Select = document.getElementById('ipLevel2');
    let level3Select = document.getElementById('ipLevel3');
    let length = level1Select.getElementsByTagName('option').length;
    ajaxRequest(url, '', 'get').then(response => {
        if(code === 0 && length < 2) {
            for(let i = 0; i < response.length; i++) {
                insertOption(response[i], level1Select);
            }
        } else if(level2Select.selectedIndex !== 0 && level1Select.selectedIndex !== 0) {
            for(let i = 0; i < response.length; i++) {
                insertOption(response[i], level3Select);
            }
        } else {
            for(let i = 0; i < response.length; i++) {
                insertOption(response[i], level2Select);
            }
        }
    });
}

function collection () {
	let url = 'http://localhost:8080/project/insert';
	let degreeSelect = document.getElementById('ipDegree');
	let degreeSelectedId = degreeSelect.selectedIndex;
	let level1Select = document.getElementById('ipLevel1');
	let level1SelectedId = level1Select.selectedIndex;
	let level2Select = document.getElementById('ipLevel2');
	let level2SelectedId = level2Select.selectedIndex;
    let level3Select = document.getElementById('ipLevel3');
    let level3SelectedId = level3Select.selectedIndex;
	if(level2SelectedId === -1) {
		alert("You should choose at least one css category")
		return false;
	}
	data = {
		title : document.getElementById('ipTitle').value,
		supervisor : document.getElementById('ipSupervisor').value,
		language : document.getElementById('ipLanguage').value,
		degree : degreeSelect[degreeSelectedId].text,
		level1 : level1Select[level1SelectedId].text,
		level2 : level2Select[level2SelectedId].text,
        level3 : level3Select[level3SelectedId].text,
		reference : document.getElementById('ipReference').value,
		description : document.getElementById('ipDescription').value
	}
	ajaxRequest(url, JSON.stringify(data), 'post').then(response => {
		console.log(response);
        if(response === 1) {
            alert("success");
            setTimeout(() => {
                window.location.href = 'index.html';
            }, 2000);
        } else if(response === 0) {
            alert("failed");
        }
	});
}

function insertion (i, select) {
	let option = document.createElement('option');
	option.innerHTML = i.name;
	select.appendChild(option);
}

function ajaxRequest (url, data, type) {
    return new Promise((resolve, reject) => {
        let req = new XMLHttpRequest();
        req.responseType = 'json';
        if(type === 'get') {
        	req.open('GET', url, true);
        } else if(type === 'post'){
        	req.open('POST', url, true);
        }
        req.withCredentials = true;
        req.setRequestHeader('Content-Type', 'application/json');
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
        if(data !== undefined) {
            req.send(data);
        } else {
            req.send();
        }
    });
}

function test0 () {
	document.getElementById('ipTitle').value = 'test';
	document.getElementById('ipDescription').value = 'test';
	document.getElementById('ipSupervisor').value = 'test';
	document.getElementById('ipReference').value = 'test';
	document.getElementById('ipLanguage').value = 'test';	
}
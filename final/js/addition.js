(function (){
	//loadCategory();
	loadCategory();
	setTimeout(() => {
		test0();
	}, 2000);
})();

function test () {
	const level1Select = document.getElementById('ipLevel1');
    const level2Select = document.getElementById('ipLevel2');
    const code = level1Select.selectedIndex;
    if(code !== 0) {
        loadCategory(code);
    } else {
        level2Select.innerHTML = '';
    }
}

function loadCategory (code) {
    let url = 'http://localhost:8080/project/level?code=' + code;
    if(code == undefined) {
        url = 'http://localhost:8080/project/level?code=0';    
    }
    ajaxRequest(url).then(response => {
    	let level1Select = document.getElementById('ipLevel1');
        let level2Select = document.getElementById('ipLevel2');
        if(code == undefined) {
            for(let i = 0; i < response.length; i++) {
                insertion(response[i], level1Select);
            }
        } else {
            level2Select.innerHTML = '';
            for(let i = 0; i < response.length; i++) {
                insertion(response[i], level2Select);
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
	if(level2SelectedId === -1) {
		alert("You should choose the css category")
		return false;
	}
	data = {
		title : document.getElementById('ipTitle').value,
		supervisor : document.getElementById('ipSupervisor').value,
		language : document.getElementById('ipLanguage').value,
		degree : degreeSelect[degreeSelectedId].text,
		level1 : level1Select[level1SelectedId].text,
		level2 : level2Select[level2SelectedId].text,
		reference : document.getElementById('ipReference').value,
		description : document.getElementById('ipDescription').value
	}
	ajaxRequest(url, JSON.stringify(data)).then(response => {
		if(response === 1) {
			alert("success");
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
        } else {
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
(function () {
	window.onload = () => {
		getData ();
	};
})();

function ajax (url) {
	return new Promise((resolve, reject) => {
        let req = new XMLHttpRequest();
        req.responseType = 'json';
        req.open('GET', url, true);
        req.withCredentials = true;
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
        req.send();
    });
}

function getData () {
	const id = sessionStorage.getItem("pid");
	const url = 'http://localhost:8080/project/' + id;
	ajax(url).then(result => {
		console.log(result);
		display(result);
	});
}

function display(p) {
	const body = document.getElementById('container');
	const title = document.createElement('b');
	const description = document.createElement('p');
	const reference = document.createElement('p');
	const language = document.createElement('p');
	const level1 = document.createElement('level1');
	const level2 = document.createElement('level2');
	title.innerHTML = p.title;
	description.innerHTML = p.description;
	reference.innerHTML = p.reference;
	language.innerHTML = p.language;
	level1.innerHTML = p.level1;
	levl2.innerHTML = p.level2;
	body.appendChild(title);
	body.appendChild(description);
	body.appendChild(reference);
	body.appendChild(language);
	body.appendChild(level1);
	body.appendChild(level2);
}
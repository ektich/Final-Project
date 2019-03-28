(function () {
    window.onload = () => {
        loadData();
        topList();
        maintainCookie();
        identifyUser();
        loadCategory();
        welcome();
        
        //test();

        
        
        //management.setAttribute('style', 'visibility: hidden');
        //addition.setAttribute('style', 'visibility: hidden');

        const body = document.getElementById('container');
        const div = document.getElementById('loading');
        body.style.display = 'none';
        const img = document.createElement('img');
        div.appendChild(img);
        img.setAttribute('src', '../images/loader.gif');

        document.getElementById('ipbtn').onclick = classifyQuery;

        
        const button = document.createElement('button');
        button.innerHTML = 'Button';
        button.addEventListener('click', () => {
            console.log(1);
            //document.getElementById('test7').remove();
        });

    }
})();

function logInBtn() {
    const div = document.getElementById('logioBtn');
    const a = document.createElement('a');
    a.setAttribute('class', 'btn-sm btn-outline-primary');
    a.setAttribute('href', '/html/login');
    a.setAttribute('id', 'alogin');
    a.innerHTML = 'Log in';
    div.appendChild(a);
}

function logOutBtn() {
    const div = document.getElementById('logioBtn');
    const a = document.createElement('a');
    a.setAttribute('class', 'btn-sm btn-outline-danger');
    a.setAttribute('href', '/logout');
    a.setAttribute('id', 'alogout');
    a.innerHTML = 'Log out';
    div.appendChild(a);
}

function welcome() {
    let user = username;
    let iden = identity;
    let wel = document.getElementById('welcome');
    const tb = document.getElementById('table-thead');
    if(user === '') {
        logInBtn();
        const login = document.getElementById('alogin');
        login.onclick = loginPage;
        tb.innerHTML = 'You have not logged in';
        wel.innerHTML = 'You have not logged in';
    } else if(username !== "" && username !== null) {
        logOutBtn();
        const logout = document.getElementById('alogout');
        logout.onclick = logOut;
        wel.childNodes[1].innerHTML = username;
    }
}

function ccsQuery() {
    let code = this.id;
    if(code !== undefined) {
        code = code.split('-').pop();
    }
    let url = 'http://localhost:8080/project/level?code=' + code;
    if(code == undefined) {
        url = 'http://localhost:8080/project/level?code=0';    
    }
    ajaxRequest(url).then(response => {
        if(code == undefined) {
            for(let i = 0; i < response.length; i++) {
                outerCategory(response[i], i);
            }
        } else {
            let ul = document.getElementById('ul-' + code);
            if(ul.innerHTML !== '') return false;
            for(let i = 0; i < response.length; i++) {
                innerCategory(response[i], i, ul);
            }
        }
        //console.log(response);
    });
}

let codeForQuery = 0;
function classifyQuery() {
    const level1Select = document.getElementById('ipLevel1');
    const level2Select = document.getElementById('ipLevel2');
    const level3Select = document.getElementById('ipLevel3');
    const level1Code = level1Select.selectedIndex;
    const level2Code = level2Select.selectedIndex;
    const level3Code = level3Select.selectedIndex;
    
    if(level3Code !== 0 && level2Code !== 0 && level1Code !== 0) {
        const code3 = level3Select[level3Code].getAttribute('data-id');
        if(code3 !== null) {
            codeForQuery = code3;
            //console.log(codeForQuery);
        }
    } else if(level2Code !== 0 && level1Code !== 0 && level3Code === 0) {
        const code2 = level2Select[level2Code].getAttribute('data-id');
        if(code2 !== null) {
            codeForQuery = code2;
            //console.log(codeForQuery);
        }
    } else if(level1Code !== 0 && level2Code === 0 && level3Code === 0){
        const code1 = level1Select[level1Code].getAttribute('data-id');
        if(code1 !== null) {
            codeForQuery = code1;
            //console.log(codeForQuery);
        }
    }
    let url = 'http://localhost:8080/project/all/1?code=' + codeForQuery;
    ajaxRequest(url).then(response => {
        classifyQueryRequest(response);
    });

    if(level1Code === 0 && level2Code === 0 && level3Code === 0) {
        alert('choose at least one level');
    }
}

function classifyQueryRequest(response) {
    if(response.size !== 0) {
        document.getElementById('project-list').innerHTML = '';
        for(let i of response.list) {
            insertion(i);
        }
        document.getElementById('page-info-list').innerHTML = '';
        pagination(response.pageNum, response.pages);
    } else {
        alert("Didn't find any projects")
    }
}

function outerCategory(i, j) {
    let outer = document.createElement('div');
    outer.setAttribute('class', 'col-md-2');
    let inner = document.createElement('div');
    inner.onclick = ccsQuery;
    let ul = document.createElement('ul');
    inner.setAttribute('class', 'dropdown-toggle');
    inner.setAttribute('id', 'dropdownMenuButton-' + (j+1));
    inner.setAttribute('data-toggle', 'dropdown');
    inner.setAttribute('type', 'button');
    inner.innerHTML = i.name;
    ul.setAttribute('id', 'ul-' + (j+1));
    ul.setAttribute('class', 'dropdown-menu');
    ul.setAttribute('aria-labelledby', 'dropdownMenuButton');
    outer.appendChild(inner);
    outer.appendChild(ul);
    if(j < 6) document.getElementById('category-content1').appendChild(outer);
    else document.getElementById('category-content2').appendChild(outer);
}

function innerCategory(i, j, ul) {
    //let ul = document.getElementById('ul-'+(j+1));
    const li = document.createElement('li');
    const a = document.createElement('a');
    a.setAttribute('class', 'dropdown-item');
    a.setAttribute('data-trigger', 'hover');
    a.setAttribute('href', '');
    a.setAttribute('id', i.id);
    a.innerHTML = i.name;
    a.onclick = queryProjectByCode;
    a.addEventListener("mouseover", () => {
        ccsQuery2(a);
    });
    li.appendChild(a);
    ul.appendChild(li);
}

function queryProjectByCode() {
    let code = this.id;
    let url = 'http://localhost:8080/project/category?code=' + code;
    ajaxRequest(url).then(response => {
        console.log(response);
    });
    return false;
}

function ajaxRequest (url) {
    return new Promise((resolve, reject) => {
        let req = new XMLHttpRequest();
        req.responseType = 'json';
        req.open('GET', url, true);
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
        req.send();
    });
}

function loadData (page, codeForQuery) {
    page = page === undefined ? 1 : page;
    codeForQuery = codeForQuery === undefined ? 0 : codeForQuery;
    let url = 'http://localhost:8080/project/all/' + page + '?code=' + codeForQuery;
    ajaxRequest (url).then(response => {
        //console.log(response);
        const body = document.getElementById('container');
        if(body.style.display === 'none') {
            body.style.display = 'block';
            document.getElementById('loading').remove();
        }
        if(response != null) {
            classifyQueryRequest(response);
        }
    });
}

function insertion (project) {
    const outerDiv = document.createElement('div');
    outerDiv.setAttribute('class', 'project-id');
    const innerDiv = document.createElement('div');
    innerDiv.setAttribute('class', 'project-title');
    const a = document.createElement('a');
    innerDiv.appendChild(a);
    a.setAttribute('href', '/projects/'+ project.id);
    a.setAttribute('class', 'subProject');
    a.innerHTML = project.title;
    a.onclick = detailsProjectPage;
    a.appendChild(document.createElement('br'));
    outerDiv.appendChild(innerDiv);
    for(let i = 1; i <= 6; i++) {
        const b = document.createElement('b');
        const span = document.createElement('span');
        if(i === 1) {
            b.innerHTML = 'Language: ';
            span.innerHTML = project.language;
        } else if(i === 2) {
            b.innerHTML = 'Supervisor: ';
            span.innerHTML = project.supervisor;
        } else if(i === 3) {
            b.innerHTML = 'Required degree: ';
            span.innerHTML = project.degree;
        } else if(i === 4){
            b.innerHTML = 'CCS: ';
            span.innerHTML = project.level1;
        } else if(i === 5) {
            span.innerHTML = project.level2;
        } else {
            span.innerHTML = project.level3;
        }
        if(i < 5) {
            outerDiv.appendChild(document.createElement('br'));
            outerDiv.appendChild(b);        
        }
        outerDiv.appendChild(document.createElement('br'));
        outerDiv.appendChild(span);
    }
    const div = document.getElementById('project-list');
    div.appendChild(outerDiv);
}

function pagination (pageNum, pages) {
    //console.log('Current page is: ' + pageNum + ' Pages: ' + pages);
    //first page li
    const bar = document.getElementById('page-info-list');
    const first = document.createElement('li');
    first.setAttribute('class', 'page-item');
    if(pageNum === 1) {
        first.classList.add('disabled');
    }
    const firstA = document.createElement('a');
    firstA.setAttribute('href', '#page-1');
    firstA.setAttribute('class', 'page-link');
    firstA.innerHTML = '&laquo;';
    firstA.onclick = () => {
        loadData(1);
        return false;
    };
    first.appendChild(firstA);
    bar.appendChild(first);
    //
    for(let i = 1; i <= 5; i++) {
        const li = document.createElement('li');
        li.setAttribute('class', 'page-item');
        const a = document.createElement('a');
        if (pageNum > 3 && pageNum + 2 <= pages) {
            if ((pageNum - 3 + i) === pageNum) {
                li.classList.add('active');
            }
            a.setAttribute('href', '#page-' + (pageNum - 3 + i));
            a.setAttribute('class', 'page-link');
            a.innerHTML = (pageNum - 3 + i) + '';
        } else if(pages > 5 && pageNum + 2 > pages) {
            if ((pages - 5 + i) === pageNum) {
                li.classList.add('active');
            }
            a.setAttribute('href', '#page-' + (pages - 5 + i));
            a.setAttribute('class', 'page-link');
            a.innerHTML = (pages - 5 + i) + '';
        } else {
            if (i === pageNum) {
                li.classList.add('active');
            }
            if (i > pages) {
                li.classList.add('disabled');
            }
            a.setAttribute('href', '#page-' + i);
            a.setAttribute('class', 'page-link');
            a.innerHTML = i + '';
        }
        if(pages - i >= 0) {
            li.onclick = turningPage;
        }
        li.appendChild(a);
        bar.appendChild(li);
    }
    //last page li
    const last = document.createElement('li');
    last.setAttribute('class', 'page-item');
    if(pageNum === pages) {
        last.classList.add('disabled');
    }
    const lastA = document.createElement('a');
    lastA.setAttribute('href', '#page-' + pages);
    lastA.setAttribute('class', 'page-link');
    lastA.innerHTML = '&raquo;';
    lastA.onclick = () => {
        loadData(pages);
        return false;
    };
    last.appendChild(lastA);
    bar.appendChild(last);
}

function turningPage () {
    let page = this.firstChild.innerHTML;
    loadData(page, codeForQuery);
    return false;
}

function topList() {
    let url = 'http://localhost:8080/project/topList';
    ajaxRequest(url).then(response => {
        //1 max 2 title 3 id
        //console.log(response);
        if(response != null) {
            const ul = document.getElementById('submit-bar-top-ul');
            for(let i of response) {
                const a = document.createElement('a');
                a.setAttribute('class','list-group-item d-flex justify-content-between align-items-center');
                a.setAttribute('href', '/projects/' + i.id);
                a.onclick = detailsProjectPage;
                const small = document.createElement('small');
                small.innerHTML = i.title;
                const span = document.createElement('span');
                span.setAttribute('class', 'badge badge-primary badge-pill');
                span.innerHTML = i.amount;
                a.appendChild(small);
                a.appendChild(span);
                ul.appendChild(a);
            }
        }
    });
}

//登陆之后studentbar/ lecturebar
function queryStates(id) {
    let url = 'http://localhost:8080/states?id=' + id;
    ajaxRequest(url).then(response => {
        //console.log(response);
        const tbody = document.getElementById('table-tbody');
        tbody.innerHTML = '';
        for(let i = 0; i < 5; i++) {
            if(response != null) {
                if(response.length > i) {
                    const tr = document.createElement('tr');
                    const th = document.createElement('th');
                    th.setAttribute('scope', 'row');
                    th.innerHTML = (i+1) + '';
                    const id = document.createElement('td');
                    id.innerHTML = response[i].id;
                    const state = document.createElement('td');
                    if(response[i].state === 'Accepted') {
                        state.setAttribute('class','accept');
                    } else if(response[i].state === 'Rejected') {
                        state.setAttribute('class','reject');
                    } else if(response[i].state === 'Pending') {
                        state.setAttribute('class','pending');
                    }
                    state.innerHTML = response[i].state;
                    tr.appendChild(th);
                    tr.appendChild(id);
                    tr.appendChild(state);
                    tbody.appendChild(tr);
                } else {
                    const tr = document.createElement('tr');
                    const th = document.createElement('th');
                    th.setAttribute('scope', 'row');
                    th.innerHTML = (i+1) + '';
                    const id = document.createElement('td');
                    id.innerHTML = 'You have not chose yet';
                    const state = document.createElement('td');
                    tr.appendChild(th);
                    tr.appendChild(id);
                    tr.appendChild(state);
                    tbody.appendChild(tr);
                }
            } else {
                const tr = document.createElement('tr');
                const th = document.createElement('th');
                th.setAttribute('scope', 'row');
                th.innerHTML = (i+1) + '';
                const id = document.createElement('td');
                id.innerHTML = 'You have not chose yet';
                const state = document.createElement('td');
                tr.appendChild(th);
                tr.appendChild(id);
                tr.appendChild(state);
                tbody.appendChild(tr);
            }
        }
    });
}

function queryProjects() {
    let url = 'http://localhost:8080/states/';
    ajaxRequest(url).then(response => {
        const tbody = document.getElementById('table-tbody');
    });
}


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
    let length = document.getElementById('ipLevel1').getElementsByTagName('option').length;
    ajaxRequest(url).then(response => {
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

function insertOption (i, select) {
    let option = document.createElement('option');
    option.innerHTML = i.name;
    option.setAttribute('data-id', i.id);
    select.appendChild(option);
}


function logOut () {
    let url = 'http://localhost:8080/logout.do';
    ajaxRequest(url).then(result => {
        document.cookie = 'username=; expires=expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/';
        document.cookie = 'identity=; expires=expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/';
        location.reload();
        console.log(document.cookie);
    });
    return false;
}

function additionPage () {
    window.location.href = 'addition.html';
    return false;
}
function loginPage () {
    window.location.href = 'login.html';
    return false;
}
function managementPage () {
    window.location.href = 'management.html';
    return false;
}

function loadTbodyS () {
    const tbody = document.getElementById('table-tbody');
    for(let i = 1; i < 6; i++) {
        const tr = document.createElement('tr');
        tbody.appendChild(tr);
        const th = document.createElement('th');
        th.setAttribute('scope', 'row');
        th.innerHTML = i;
        const td1 = document.createElement('td');
        const td2 = document.createElement('td');
        tr.appendChild(th);
        tr.appendChild(td1);
        tr.appendChild(td2);
    }
}

function loadTbodyT (length) {
    const tbody = document.getElementById('table-tbody');
    for(let i = 0; i < length; i++) {
        const tr = document.createElement('tr');
        tbody.appendChild(tr);
        const th = document.createElement('th');
        th.setAttribute('scope', 'row');
        th.innerHTML = i;
        const td1 = document.createElement('td');
        const td2 = document.createElement('td');
        tr.appendChild(th);
        tr.appendChild(td1);
        tr.appendChild(td2);
    }
}

function loadThead (identity) {
    const thead = document.getElementById('table-thead');
    const tr = document.createElement('tr');
    thead.appendChild(tr);
    const th1 = document.createElement('th');
    const th2 = document.createElement('th');
    const th3 = document.createElement('th');
    th1.innerHTML = '#';
    th2.innerHTML = 'Project Id';
    identity === 'student' ? th3.innerHTML = 'Number' : th3.innerHTML = 'Status';
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
}


function test10 () {
    let url = 'http://localhost:8080/testTimeOut';
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

function testTimeOut() {
    checkTimeOut();
    test10().then(result => {
        console.log(result.username);
        //localStorage.setItem(result.username, result.username.value);
    });
}

function detailsProjectPage () { 
    //console.log(this.href.split('/').pop());
    const id = this.href.split('/').pop();
    sessionStorage.setItem("pid", id); 
    window.location.href = 'project.html';
    return false;
};


function test6() {
    const id = this.href.split('/').pop();
    sessionStorage.setItem("pid", id);
    return false;
}
//insertBefore

function identifyUser() {
    const data = identity;
    if(data === 'student') { 
        loadTbodyS();
        loadThead('student');
        queryStates(username);
    } else if(data === 'lecture') {
        loadThead('lecutre');
        const navbar = document.getElementById('navbar');
        const a = document.createElement('a');
        a.setAttribute('class', 'btn-sm btn-outline-success');
        a.setAttribute('href', '/management');
        a.setAttribute('id', 'amanagement');
        a.innerHTML = 'Management';
        navbar.appendChild(a);
        const management = document.getElementById('amanagement');
        management.onclick = managementPage;
        document.getElementById('submit-bar-submitted').remove();
    }
}

function test() {
    let data = 'username=' + sessionStorage.getItem('username');
    let url = 'http://localhost:8080/project/dispose';
    ajaxRequest(url, data).then(result => {
        console.log(result);
        
    });
}

var username = '';
var identity = '';
function maintainCookie() {
    let cookie = [];
    cookie = document.cookie.split("; ");
    for(let i of cookie) {
        const s1 = i.split("=")[0];
        const s2 = i.split("=")[1];
        if(s1 === 'username') {
            username = s2;
        } else if(s1 === 'identity') {
            identity = s2;
        }
    }
}

function checkTimeOut() {
    if(username === '') {
        window.location = 'login.html';
    }
}
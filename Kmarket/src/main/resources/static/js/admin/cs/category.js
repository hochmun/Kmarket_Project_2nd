function typeChange() {
    const adress = window.location.href;
    const path = adress.substring(0, adress.lastIndexOf('list'));
    const type = document.querySelector('select[name=type]').value;
    
    location.href = path+"list?type="+type;
}

function cate2Change() {
    const adress = window.location.href;
    const path = adress.substring(0, adress.lastIndexOf('list'));
    const cate1 = document.querySelector('select[name=cate1]').value;
    const cate2 = document.querySelector('select[name=cate2]').value;

    location.href = path+"list?cate1="+cate1+"&cate2="+cate2;
}

function cate1Change() {
    const select = document.querySelector('select[name=cate1]');
    const jsondata = {"cate1":select.value};

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/Kmarket/admin/cs/getCsCate2", true);
    xhr.responseType = "json";

    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE) {
            if(xhr.status != 200) alert("Request fail...")
            else {
                const data = xhr.response;
                const cate2Select = document.querySelector('select[name=cate2]')

                cate2Select.replaceChildren();

                const option = document.createElement('option');
                option.setAttribute('value', '');
                option.innerText = '2차 선택';
                cate2Select.appendChild(option);

                for(const vo of data.cate2VOs) {
                    const option2 = document.createElement('option');
                    option2.setAttribute('value', vo.cate2);
                    option2.innerText = vo.cate2Name;
                    cate2Select.appendChild(option2);
                }

            }
        }
    }

    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(jsondata));
}
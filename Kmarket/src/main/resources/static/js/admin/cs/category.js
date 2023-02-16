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

function articleWrite(type) {
    event.preventDefault;

    let cate1 = '-1';
    let cate2 = '-1';
    let noticeType = '-1';

    if(type == 'faq') {
        cate1 = document.querySelector('select[name=cate1]').value;
        cate2 = document.querySelector('select[name=cate2]').value;

        if(cate1 == '' || cate2 == '') {
            alert('카테고리를 선택하여 주십시오');
            return;
        }

    }
    if(type == 'notice') {
        noticeType = document.querySelector('select[name=type]').value;

        if(noticeType == '') {
            alert('카테고리를 선택하여 주십시오');
            return;
        }

    }

    const title = document.querySelector('input[name=title]').value;
    const Content = document.querySelector('textarea[name=content]').value;
    
    const jsondata = {"cate1":cate1,"cate2":cate2,"type":noticeType,
                    "title":title,"content":Content};

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/Kmarket/admin/cs/"+type+"/write", true);
    xhr.responseType = "json";

    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE) {
            if(xhr.status != 200) alert("Request fail...")
            else {
                const data = xhr.response;
                
                if (data.result > 0) {
                    // 성공
                    alert('게시물 작성에 성공 하였습니다.');
                    location.href = "/Kmarket/admin/cs/"+type+"/list";
                } else if (data.result == -1) {
                    // faq 게시물 갯수 10개 이상 일때
                    alert('해당 카테고리의 게시물이 10개 이상 이므로 더이상 게시물을 작성 하실 수 없습니다.');
                    location.href = "/Kmarket/admin/cs/"+type+"/list";
                } else {
                    // 실패
                    alert('게시물 작성에 실패 하였습니다.')
                    location.href = "/Kmarket/admin/cs/"+type+"/list";
                }

            }
        }
    }

    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(jsondata));
}
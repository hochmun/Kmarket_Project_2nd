// notice 카테고리 변경 스크립트
// 카테고리 변경시 페이지 이동
function typeChange() {
    const adress = window.location.href;
    const path = adress.substring(0, adress.lastIndexOf('list'));
    const type = document.querySelector('select[name=type]').value;
    
    location.href = path+"list?type="+type;
}

// faq, qna 카테고리 변경 스크립트
// 카테고리 변경시 페이지 이동
function cate2Change() {
    const adress = window.location.href;
    const path = adress.substring(0, adress.lastIndexOf('list'));
    const cate1 = document.querySelector('select[name=cate1]').value;
    const cate2 = document.querySelector('select[name=cate2]').value;

    location.href = path+"list?cate1="+cate1+"&cate2="+cate2;
}

// faq, qna 카테고리 변경 스크립트
// 카테고리 변경시 cate2 리스트를 동적으로 불러옴
// cate1값이 ''일때 실행 취소
function cate1Change() {
    const select = document.querySelector('select[name=cate1]');

    if(select.value == '') return;

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

// 게시물 작성 스크립트
// type => faq, notice 구별 용
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

// 게시물 수정 스크립트
// type => faq, notice 구별 용
// no   => 게시물 번호
function articleModify(type, no) {
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
                    "title":title,"content":Content,"no":no};

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/Kmarket/admin/cs/"+type+"/modify", true);
    xhr.responseType = "json";

    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE) {
            if(xhr.status != 200) alert("Request fail...")
            else {
                const data = xhr.response;

                if (data.result > 0) {
                    // 성공
                    alert('게시물 수정에 성공 하였습니다.');
                    goBack(2);
                } else if (data.result == -1) {
                    // faq 게시물 갯수 10개 이상 일때
                    alert('해당 카테고리의 게시물이 10개 이상 이므로 해당 카테고리로 게시물을 수정 하실 수 없습니다.');
                    window.location.reload();
                } else {
                    // 실패
                    alert('게시물 수정에 실패 하였습니다.');
                    window.location.reload();
                }
            }
        }
    }

    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(jsondata));
}

// 2023/02/16 // 심규영 // 문의하기 답변 스크립트
function articleReply(articleNo) {
    event.preventDefault();

    const content = document.querySelector('textarea[name=AdminContent]').value;

    const jsondata = {"no":articleNo,"qnaAdminContent":content};

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/Kmarket/admin/cs/qna/reply", true);
    xhr.responseType = "json";

    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE) {
            if(xhr.status != 200) alert("Request fail...")
            else {
                const data = xhr.response;

                if(data.result > 0) {
                    // 성공
                    alert('문의 사항 답변에 성공 했습니다.');

                    let adress = window.location.href;
                    const adress2 = adress.replace('reply', 'view');

                    location.href = adress2;

                } else {
                    // 실패
                    alert('문의 사항 답변에 실패 했습니다.');
                    window.location.reload()
                }
            }
        }
    }

    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(jsondata));
}

// 2023/02/16 // 심규영 // 단일 게시글 삭제 스크립트
// type     => 게시글 종류
// no       => 게시글 번호
// type2    => 게시글 보기, 답변(view, reply)
function articleDelete(type, no, type2) {
     event.preventDefault();

     const jsondata = {"no":no};
     console.log(typeof(jsondata));
     console.log(typeof(JSON.stringify(jsondata)));

     const xhr = new XMLHttpRequest();
     xhr.open("POST", "/Kmarket/admin/cs/"+type+"/delete", true);
     xhr.responseType = "json";

     xhr.onreadystatechange = function() {
         if(xhr.readyState == XMLHttpRequest.DONE) {
             if(xhr.status != 200) alert("Request fail...")
             else {
                 const data = xhr.response;

                 if(data.result > 0) {
                     // 성공
                     alert('게시물 삭제에 성공 하셨습니다.');

                     let adress = window.location.href;
                     const adress2 = adress.replace(type2, 'list');

                     location.href = adress2;

                 } else {
                     // 실패
                    alert('게시물 삭제에 실패 하였습니다.');
                    window.location.reload();
                 }
             }
         }
     }

     xhr.setRequestHeader("Content-type", "application/json");
     xhr.send(JSON.stringify(jsondata));
}

// 2023/02/17 // 심규영 // 다중 게시글 삭제 스크립트
// type  => 삭제하는 게시물 종류 (faq, notice, qna)
function articlesDelete(type) {
    // 이벤트 실행 취소
    event.preventDefault();

    // 삭제 의사 묻기
    const deleteOk = confirm('삭제 하시겠습니까?');
    if(deleteOk == false) return;

    // 체크된 input 가져오기
    const checkedArticle = document.querySelectorAll('input[name=상품코드]:checked')

    // 가져온 목록 0일 경우 리턴
    if(checkedArticle.length == 0) {
        alert('상품을 선택해 주세요');
        return;
    }

    // 게시물 no를 담을 문자열
    let arrayArticleNo = '';

    // 문자열에 , 단위로 no 담기
    checkedArticle.forEach((input)=>{arrayArticleNo += input.parentElement.nextElementSibling.innerText+',';})

    console.log(typeof(arrayArticleNo));
    

    // 전송
    articleDelete(type, arrayArticleNo, 'list');
}

// 페이지 뒤로 이동
// number 수 만큼 뒤로 이동
// number 가 음수면 앞으로 이동
function goBack(number) {
    event.preventDefault();
    window.history.go(-number);
}
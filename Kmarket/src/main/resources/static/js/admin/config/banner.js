/*
    날짜 : 2023/02/23
    이름 : 이해빈
    내용 : 관리자 배너 스크립트

*/

$(function() {

    $("#tabs").tabs();

    // 팝업 닫기
    $('.btnClose').click(function(){
        $(this).closest('.popup').removeClass('on');
    });

    // 배너등록 팝업 띄우기
    $('.btnRegister').click(function(e){
        e.preventDefault();
        $('#bannerRegister').addClass('on');
    });

    // 각 탭에 대해 함수 실행
    ['tabs1', 'tabs2', 'tabs3', 'tabs4', 'tabs5'].forEach((tab) => {

        // 체크박스 선택 함수를 호출
        const checkAll = document.querySelector(`#${tab} input[name=all]`);
        const checkboxes = document.querySelectorAll(`#${tab} input[name=배너번호]`);
        toggleCheckboxes(checkAll, checkboxes);

        // 삭제 함수 호출
        $(`#${tab} .btnNegative`).click(function(){
            deleteBanner(tab);
        });

    });

    // 배너 등록 유효성 검사
    $(document).on('click', '#bannerRegister .btnPositive', function(e) {

        let name = $('input[name=bannerName]').val();
        let size = $('input[name=bannerSize]').val();
        let color = $('input[name=bannerColor]').val();
        let link = $('input[name=bannerLink]').val();
        let position = $('select[name=bannerPosition]').val();
        let sdate = $('input[name=bannerSdate]').val();
        let edate = $('input[name=bannerEdate]').val();
        let stime = $('input[name=bannerStime]').val();
        let etime = $('input[name=bannerEtime]').val();


        if(name == ''){
            alert('배너명을 입력하세요.');
            return false;
        }

        if(size == ''){
            alert('배너사이즈를 입력하세요.');
            return false;
        }

        if(color == ''){
            alert('배너 배경색를 입력하세요.');
            return false;
        }

        if(link == ''){
            alert('배너 링크를 입력하세요.');
            return false;
        }

        if(position == 0){
            alert('배너 위치를 선택하세요.');
            return false;
        }

        if(sdate == ''){
            alert('노출 시작날짜를 선택하세요.');
            return false;
        }
        if(edate == ''){
            alert('노출 종료날짜를 선택하세요.');
            return false;
        }

        if(stime == ''){
            alert('노출 시작시간을 선택하세요.');
            return false;
        }
        if(etime == ''){
            alert('노출 종료시간을 선택하세요.');
            return false;
        }

        if(sdate > edate){
            alert('노출 종료날짜가  노출 시작날짜보다 이릅니다. \n 다시 선택해주세요.');
            return false;
        }

        /*
        if(stime > etime){
            alert('노출 종료시간이  노출 시작시간보다 이릅니다. \n 다시 선택해주세요.');
            return false;
        }
        */

    });


    // 배너 위치에 따라 배너 사이즈 변경되게 설정
    $('select[name=bannerPosition]').on('change', function(){

        let val = $(this).val();
        let size = $('input[name=bannerSize]').val();

        switch(val){

            case 'MAIN1' :
                $('input[name=bannerSize]').val('1200x80');
                break;
            case 'MAIN2' :
                $('input[name=bannerSize]').val('985x447');
                break;
            case 'PRODUCT1' :
                $('input[name=bannerSize]').val('456x60');
                break;
            case 'MEMBER1' :
                $('input[name=bannerSize]').val('425x260');
                break;
            case 'MY1' :
                $('input[name=bannerSize]').val('810x86');
                break;

        }
    });
});


// 체크박스 활성화 함수
function bannerStatus(no, status){

    //let bannerStatus = 	$(this).closest('input[name=bannerStatus]');
    //alert('bannerstatus' + bannerStatus);
    //console.log(bannerStatus);

    let isOk = null;

    if(status == 0){
        isOk = confirm('배너를 비활성화 하시겠습니까?');
    }else if(status == 1){
        isOk = confirm('배너를 활성화 하시겠습니까?');
    }

    if(!isOk){
        return false;
    }

    // ajax
    $.ajax({
        url: '/Kmarket/admin/config/changeBannerStatus',
        method : 'post',
        data : {"no": no, "status" : status},
        dataType : 'json',
        success : function(data){
            if(data.result > 0){

                if(status == 0){
                    alert('선택하신 배너를 비활성화하였습니다.');
                }else if(status == 1){
                    alert('선택하신 배너를 활성화 하였습니다.')
                }

            }else{
                alert('요청을 실패하였습니다. \n잠시 후 다시 시도해 주세요.');
            }
        }
    });

}


// 선택삭제 함수
function deleteBanner(tab){

    const checkboxes = document.querySelectorAll(`#${tab} input[name=배너번호]:checked`);

    let Ok = confirm('선택한 배너들을 삭제하시겠습니까?');

    if(!Ok){
        return false;
    }

    // 선택된 배너들만 배열에 넣기
    let checkboxArr = [];
    for(let i=0; i < checkboxes.length; i++){

        if(checkboxes[i].checked){
            let no = checkboxes[i].value;
            checkboxArr.push(no);

            // 목록에서 숨기기
            let tr = checkboxes[i].parentNode.parentNode;
            tr.style.display='none';
        }
    }

    console.log(checkboxArr);

    if(checkboxArr == ''){
        alert('선택된 배너가 없습니다!')
        return false;
    }

    // ajax
    $.ajax({
        url: '/Kmarket/admin/config/deleteBanner',
        method : 'post',
        data : JSON.stringify({"checkboxArr": checkboxArr}),
        contentType: 'application/json',
        dataType : 'json',
        success : function(data){
            if(data.result > 0){

                alert('선택하신 배너를 성공적으로 삭제하였습니다.');

            }else{
                alert('배너 삭제에 실패하였습니다. \n잠시 후 다시 시도해 주세요.');
            }
        }
    });

}


// 체크박스 선택 함수
function toggleCheckboxes(checkAll, checkboxes) {

    // 전체 선택 , 해제
    checkAll.addEventListener('click', () => {
        for (let i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = checkAll.checked;
        }
    });

    // 개별 체크박스를 누르면 전체선택 해제
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].addEventListener('click', () => {
            if (!this.checked) {
                checkAll.checked = false;
            }
        });
    }
}
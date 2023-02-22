/*
    날짜 : 2023/02/21
    이름 : 이해빈
    내용 : 마이페이지 info 자바스크립트
*/


$(document).ready(function(){

    // 이메일 변경
    $('#btnChangeEmail').click(function(){

        // 입력가능하게 readonly 속성제거
        $('input[name=email1]').removeAttr('readonly');
        $('input[name=email2]').removeAttr('readonly');

        $("#domain").change(function() {
          let domain = $(this).val();
          $('input[name=email2]').attr('value', domain);
        });


    });

    // 휴대폰 번호 변경
    $('#btnChangeHp').click(function(){

        // 입력가능하게 readonly 속성제거
        $('input[name=hp1]').removeAttr('readonly');
        $('input[name=hp2]').removeAttr('readonly');
        $('input[name=hp3]').removeAttr('readonly');

    });


    // 최종 회원정보 수정
    $('#btnInfoChange').click(()=>{

        let uid = $('input[name=uid]').val();

        let email1 = $('input[name=email1]').val();
        let email2 = $('input[name=email2]').val();

        let email = email1 + '@' + email2;

        let hp1 = $('input[name=hp1]').val();
        let hp2 = $('input[name=hp2]').val();
        let hp3 = $('input[name=hp3]').val();
        let hp = hp1 + '-' + hp2 + '-' + hp3;

        let zip = $('input[name=zip]').val();
        let addr1 = $('input[name=addr1]').val();
        let addr2 = $('input[name=addr2]').val();

        // 유효성 검사
        if(email1 == '' || email2 == ''){
            alert('이메일을 입력해주세요.');
            return false;
        }

        if(hp1 == '' || hp2 == '' || hp3 == ''){
            alert('휴대폰 번호를 올바르게 입력해주세요.');
            return false;
        }

        if(zip == '' || addr1 == ''){
            alert('주소를 입력해주세요.');
            return false;
        }

        jsonData = {
            "uid" : uid,
            "email" : email,
            "hp" : hp,
            "zip": zip,
            "addr1" : addr1,
            "addr2" : addr2
        }

        let isOk = confirm('회원정보를 수정하시겠습니까?');

        if(isOk){
            // ajax
            $.ajax({
                url: '/Kmarket/my/info',
                method : 'post',
                data : jsonData,
                dataType : 'json',
                success : function(data){
                    if(data.result > 0){

                        alert('회원정보가 성공적으로 수정되었습니다.');

                    }else{
                        alert('회원정보 수정에 실패하였습니다. \n잠시 후 다시 시도해 주세요.');
                    }
                }
            });
        }

    });
});
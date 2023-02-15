/**
* 사용자 회원가입 검증
 */
 
 
	// 데이터 검증에 사용하는 정규표현식
	let reUid   = /^[a-z]+[a-z0-9]{5,19}$/g;
	let rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
	let reCompany  	= /^[(\\(주\\))]+[가-힣]+$/;
	let reManager  = /^[가-힣]+$/;
	let reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	let reManagerHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
	let reAuth  = /^[0-9]+$/;
	let reCorp		= /^(\d{3,3})+[-]+(\d{2,2})+[-]+(\d{5,5})$/;
    let reTel 		= /^\d{2,3}-\d{3,4}-\d{4}$/;
    let reFax 		= /^\d{2,3}-\d{3,4}-\d{4}$/;
    let reCeo  		= /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
    let reOnline 	= /^([ㄱ-힣]{2,2})-(\d{5,5})$/;

	
	// 폼 데이터 검증 결과 상태변수
	let isUidok = false;
	let isPassok = false;
	let isCompanyok = false;
	let isManagerok = false;
	let isNickok = false;
	let isEmailok = false;
	let isEmailAuthOk = false;
	let isManagerHpok = false;
	let isCorpok = false;
    let isTelok = false;
    let isFaxok = false;
    let isCeook = false;
    let isZipok	= false;
    let isaddr1ok = false;
    let isaddr2ok = false;
    let isOnlineok = false;


	let email;
	let emailCode = -999999999;
	let isClick = false;


	$(function () {
		// 아이디 검사하기
		$("input[name='uid']").keydown(function () { // 다른 아이디 값을 입력 했을때 다시 중복 확인을 해야 하므로
			isUidok = false;
		});
		
		$("input[name='uid']").focusout(function(){
			
			
			let uid = $("input[name='uid']").val();	// uid 입력값
			console.log('uid: ' +uid);
			if(isUidok){
				return;
			}
			
			// uid 값을 JSON 형태로 변환
			let jsonData = {
				"uid":uid
			};
			
			// 유효성 검사
			if(!uid.match(reUid)){
				isUidok = false;
				$('.msgId').hide();
				$('.uidResult').css('color', 'red').text('유효한 아이디가 아닙니다.');
				return;
			}
			
			
			setTimeout(function(){
				$.ajax({
					url: '/Kmarket/member/checkUid',
					method: 'get',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						if(data.result==0){
							isUidok = true;
							$('.msgId').hide();
							$('.uidResult').css('color', 'green').text('사용 가능한 아이디입니다.');
						}else{
							isUidok = false;
							$('.msgId').hide();
							$('.uidResult').css('color', 'red').text('이미 사용 중인 아이디입니다.');
						}
					}
				});
			}, 100);
			
		});
		
			
		// 비밀번호 재입력 일치 검사하기
		$('input[name=pass1], input[name=pass2]').focusout(function () {
			
			let pass1 = $('input[name=pass1]').val();
			let pass2 = $('input[name=pass2]').val();
			
			console.log('pass1: ' +pass1);
			console.log('pass2: ' +pass2);
			
			if(pass1.match(rePass)){
				
				$('.msgPass1').hide();
				
				if(pass1 == pass2){
					isPassok = true;
					$('.msgPass1').css('color','black');
					$('.msgPass2').hide();
					$('.passResult2').css('color', 'green').text('사용하실 수 있는 비밀번호입니다.');
				} else {
					isPassok = false;
					$('.passResult2').css('color', 'red').text('비밀번호가 일치하지 않습니다.');					
				}
				
				
			} else{
				isPassok = false;
				$('.msgPass1').css('color', 'red');	//영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.
			}
			
		});
		
		
		//회사명 확인
        $('input[name=company]').focusout(function(){
            let company = $('input[name=company]').val();

            if(company.match(reCompany)){
                isCompanyok = true;
                $('.msgCompany').css('color', 'green').text('사용가능합니다.');
            }else{
                isCompanyok = false;
                $('.msgCompany').css('color', 'red').text('유효한 회사명이 아닙니다.');
            }

        });

        //대표 확인
        $('input[name=ceo]').focusout(function(){
            let ceo = $('input[name=ceo]').val();

            if(ceo.match(reCeo)){
                isCeook = true;
                $('.msgCeo').css('color', 'green').text('사용가능합니다.');
            }else{
                isCeook = false;
                $('.msgCeo').css('color', 'red').text('유효한 대표명이 아닙니다.');
            }

        });

        //사업자등록번호
        $('input[name=bizRegNum]').focusout(function(){
            let crop = $('input[name=bizRegNum]').val();

            if(crop.match(reCorp)){
                isCorpok = true;
                $('.msgCorp').css('color', 'green').text('사용가능합니다.');
            }else{
                isCorpok = false;
                $('.msgCorp').css('color', 'red').text('유효한 사업자번호가 아닙니다.');
            }

        });

        //통신판매업신고번호
        $('input[name=comRegNum]').focusout(function(){
            let online = $('input[name=comRegNum]').val();

            if(online.match(reOnline)){
                isOnlineok = true;
                $('.msgOnline').css('color', 'green').text('사용가능합니다.');
            }else{
                isCorpok = false;
                $('.msgOnline').css('color', 'red').text('유효한 통신판매업번호가 아닙니다.');
            }

        });

        //전화번호
        $('input[name=tel]').focusout(function(){
            let tel = $('input[name=tel]').val();

            if(tel.match(reTel)){
                isTelok = true;
                $('.msgTel').css('color', 'green').text('사용가능합니다.');
            }else{
                isTelok = false;
                $('.msgTel').css('color', 'red').text('유효한 전화번호가 아닙니다.');
            }

        });

        $('input[name=fax]').focusout(function(){
            let tel = $('input[name=fax]').val();

            if(tel.match(reTel)){
                isTelok = true;
                $('.msgFax').css('color', 'green').text('사용가능합니다.');
            }else{
                isTelok = false;
                $('.msgFax').css('color', 'red').text('유효한 팩스번호가 아닙니다.');
            }

        });

        // 이메일 검사하기
        $('input[name=email]').focusout(function () {

            let email = $(this).val();

            console.log('email: ' +email);

            if(email.match(reEmail)){
                isEmailok = true;
                $('.emailResult').css('color', 'green').text('사용가능한 이메일입니다.');
            } else{
                isEmailok = false;
                $('.emailResult').css('color', 'red').text('유효하지 않는 이메일입니다.');
            }
        });


        //회사주소
        let zip = $('input[name=zip]').val();
        let zip1 = $('input[name=addr1]').val();
        let zip2 = $('input[name=addr2]').val();

        if(zip == '' && zip1 == '' && zip2 == ''){
            console.log('test');
            isZipok = false;
            isaddr1ok = false;
            isaddr2ok = false;
        }else{
            isZipok = true;
            isaddr1ok = true;
            isaddr2ok = true;
        }

        //담당자 이름
        $('input[name=manager]').focusout(function(){
            let manager = $('input[name=manager]').val();

            if(manager.match(reManager)){
                isManagerok = true;
                $('.msgManager').css('color', 'green').text('사용가능합니다.');
            }else{
                isManagerok = false;
                $('.msgManager').css('color', 'red').text('유효한 이름이 아닙니다.');
            }

        });

        //담당자 휴대폰
        $('input[name=managerHp]').focusout(function(){
            let managerHp = $('input[name=managerHp]').val();

            if(managerHp.match(reManagerHp)){
                isManagerHpok = true;
                $('.msgManagerHp').css('color', 'green').text('사용가능합니다.');
            }else{
                isManagerHpok = false;
                $('.msgManagerHp').css('color', 'red').text('유효한 번호가 아닙니다.');
            }

        });
		
		// 최종 폼 전송할 때
		
		$('.register > form').submit(function () {
			//아이디 검증
            if(!isUidok){
                alert('아이디를 확인하십시오');
                return false;
            }

            //비밀번호검증
            if(!isPassokok){
                alert('비밀번호를 확인하십시오');
                return false;
            }

            //회사명
            if(!isCompanyok){
                alert('회사명을 확인하십시오');
                return false;
            }

            //대표자
            if(!isCeook){
                alert('대표자를 확인하십시오');
                return false;
            }

            //사업자등록번호
            if(!isCorpok){
                alert('사업자등록번호를 확인하십시오');
                return false;
            }

            //통신판매업신고
            if(!isOnlineok){
                alert('통신판매업신고를 확인하십시오');
                return false;
            }

            //전화번호
            if(!isTelok){
                alert('전화번호을 확인하십시오');
                return false;
            }

            //팩스번호
            if(!isFaxok){
                alert('팩스번호을 확인하십시오');
                return false;
            }

            //이메일
            if(!isEmailokok){
                alert('이메일을 확인하십시오');
                return false;
            }

            //회사주소
            if(!isZipok){
                alert('주소를 확인하십시오');
                return false;
            }

            if(!isaddr1ok){
                alert('주소를 확인하십시오');
                return false;
            }

            if(!isaddr2ok){
                alert('주소를 확인하십시오');
                return false;
            }

            //담당자
            if(!isManagerok){
                alert('담당자를 확인하십시오');
                return false;
            }

            //휴대폰
            if(!isManagerHpok){
                alert('휴대폰을 확인하십시오');
                return false;
            }

			return true;
		});
	});
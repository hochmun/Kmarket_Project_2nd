/**
* 사용자 회원가입 검증
 */
 
 
	// 데이터 검증에 사용하는 정규표현식
	let reUid   = /^[a-z]+[a-z0-9]{5,19}$/g;
	let rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
	let reName  = /^[가-힣]+$/;
	let reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	let reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
	let reAuth  = /^[0-9]+$/;

	
	// 폼 데이터 검증 결과 상태변수
	let isUidok = false;
	let isPassok = false;
	let isNameok = false;
	let isNickok = false;
	let isEmailok = false;
	let isEmailAuthOk = false;
	let isHpok = false;

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
		
		
		// 이름 검사하기
		$('input[name=name]').focusout(function () {
			let name = $(this).val();
			
			console.log('name: '+name);
			
			if(name.match(reName)){
				isNameok = true;
				$('.nameResult').css('color', 'green').text('사용가능한 이름 입니다.');
			} else {
				isNameok = false;
				$('.nameResult').css('color', 'red').text('유효한 이름이 아닙니다.');
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
		
		
		// 휴대폰 검사하기
		$('input[name=hp]').focusout(function () {
			
			let hp = $(this).val();
			
			console.log('hp: '+hp);
			
			if(hp.match(reHp)){
				isHpok = true;
				$('.hpResult').css('color', 'green').text('사용가능한 휴대폰입니다.');
			} else{
				isHpok = false;
				$('.hpResult').css('color', 'red').text('유효하지 않는 휴대폰입니다.');
			}
		});


		
		// 최종 폼 전송할 때
		
		$('.register > form').submit(function () {
			// 아이디 검증
			if(!isUidok){ 
				alert('아이디를 확인 하십시요.');
				return false;
			}
			
			// 비밀번호 검증
			if(!isPassok){
				alert('비밀번호가 유효하지 않습니다.');
				return false;
			}
			
			// 이름 검증
			if(!isNameok){
				alert('이름이 유효하지 않습니다.');
				return false;
			}
			
		
			// 이메일 검증
			if(!isEmailok){
				alert('이메일이 유효하지 않습니다.');
				return false;
			}
			
			// 휴대폰 검증
			if(!isHpok){
				alert('휴대폰이 유효하지 않습니다.');
				return false;
			}
			
			return true;
		});
	});
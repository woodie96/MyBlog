 window.onload = function(){
	  
	  $('#submit').on('click',function(){
		 
		  var test = confirm('수정하신 내용을 저장하시겠습니까?');
		  
		  if(test === true){
			  
		  }else if(test === false){
			  
			  alert('취소되었습니다.');
			  return false;
			  
		  }  
	  });



/////////////////////////////////////////////////////	 댓글 
	  	  
	$('#replybutton').on('click',function(){
		  
		var username = $('#rusername').val();
		var nickname = $('#rnickname').val();
		var rnum = $('#rnum').val();
		var rcomment  = $('#rcomment').val();
		
		if(nickname == 'noUser'){
			
			alert('로그인 후 작성하실 수 있습니다.');
			history.go(0);
			return false;
			
		}else if(nickname != 'noUser'){
			
			 $.ajax({
				  url: 'reply',
				  type: 'post',
				  dataType: 'html',
				  data: {username: username, nickname:nickname, rnum:rnum, rcomment: rcomment},
				  success: function(data){
					  alert('정상적으로 저장되었습니다.');
					  history.go(0);
				  },
				  error: function(request, error){
					  alert("fail");

						// error 발생 이유를 알려준다.

					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				  }
				  
			  });
		}
		
	  });
	
		
///////////////////////////////////////////////////////////// 댓글부분 접었다 펼치기
		
		$('#more').click(function(){
			
			if($(this).parent().children('div,reply').is(':visible')){
				
				$(this).parent().children('div,reply').css('display','none');
				$(this).html('답글보기');
				
			}else{
				
				$(this).parent().children('div,reply').css('display','block');
				$(this).html('숨기기');
			}
		});
//////////////////////////////////////////////////// 대댓글
		
		$('#recommentButton').click(function(){
			
			var replynum = $('#replynum').val(); // 댓글 그룹 넘버
			var replypostnum = $('#replypostnum').val();
			var replyname = $('#replyname').val();
			var replynickname = $('#replynickname').val();
			var replycomment = $('#replycomment').val();
			
			//대댓글을 쓰고 저장 버튼을 눌렀을때 닉네임이 노 로그인이면 작성 불가능.
			if(replynickname == 'noLogin'){
				
				alert('로그인 후 사용이 가능합니다.');
				return false;
				
			}else if(replynickname != 'noLogin'){
				
				$.ajax({
					
					url: 'saveRecomment',
					type: 'post',
					dataType: 'html',
					data: {replynum: replynum, replyname: replyname, replynickname: replynickname, replypostnum: replypostnum, replycomment: replycomment},
					success:function(result){
						alert('답글이 정상적으로 저장되었습니다.');
						history.go(0);
					},
					error:function(error){
						alert('실패하였습니다. 관리자에게 문의해주세요.');
					}
					
				});
			}
			
		});
		
		//게시물 삭제 체크
		$('#delBtn').on('click',function(){
			
			var Delch = confirm('해당 게시물을 삭제하시겠습니까?');
			
			if(Delch == true){
				return true
			}else{
				alert('글 삭제가 취소되었습니다.');
				return false;
			}
			
		});
		
		//로그아웃 체크 버튼
	  
  }
  	window.onload = function(){
  		
  		//로그아웃 로직
  		$('#logoutBtn').on('click',function(){
  			
  			var test = confirm('로그아웃 하시겠습니까?');
  			
  			if(test == true){
  				$('#logout').submit();
  			}else{
  				return false;
  			}
  			
  		});
  		
  		//게시물 업로드 전 체크
  		$('#form').on('submit', function() {
			
  			var inputname = document.getElementById('upfile');
  			var files = inputname.files;
  			

  			
  			if($('#btitle').val().length === 0){
  				alert('제목을 입력해주세요');
  				$('#btitle').focus();
  				return false;
  			}else if(files.length > 2 || files.length < 2){
  				alert('파일 첨부는 2장만 됩니다.');
  				$('#upfile').focus();
  				return false;
  			}else if($('#bcontent').val().length === 0){
  				alert('내용을 입력해주세요');
  				$('#bcontent').focus();
  				return false;
  			}
  			
		});
  		
  		
  	}
  
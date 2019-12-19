window.onload = function(){
  		
  		var error = '${error}';
  		  		
  		$('#logoutbtn').on('click',function(){
  			
  			var yn = confirm('로그아웃 하시겠습니까?');
  			
  			if(yn){
  				//yes
  				$('#logout').submit();
  				return true;
  			}else{
  				//no	
  				return false;
  			}
  			
  		});
  		
		  
		  if(error == 'e'){
			  alert('검색하신 단어가 존재하지 않습니다.');
			  history.go(0);
		  }
  	}
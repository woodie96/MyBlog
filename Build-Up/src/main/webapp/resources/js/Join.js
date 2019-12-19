
window.onload = function(){
    
    $('#form').on('submit',function(event){
        if($('#userid').val().length ===0){
            alert('아이디를 입력해주세요!');
            event.preventDefault();
        }else if($('#username').val().length ===0){
            alert('이름을 입력해주세요!');
            event.preventDefault();
        }else if($('#nickname').val().length ===0){
            alert('별명을 입력해주세요!');
            event.preventDefault();
        }else if($('#userpw').val().length ===0){
            alert('패스워드를 입력해주세요!');
            event.preventDefault();
        }else if($('#userpwch').val().length ===0){
            alert('패스워드 확인을 하지 않으셨습니다!');
            event.preventDefault();
        }else if($('#email').val().length === 0){
            alert('이메일을 입력해주세요!');
            event.preventDefault();
        }
    });
    
    
    $('#userpwch').blur(function(){
       if($('#userpw').val() != "" && $('#userpwch').val() != ""){
          if($('#userpw').val() === $('#userpwch').val()){
              alert('비밀번호가 일치합니다.')
              $('#submit').removeAttr('disabled');
          }else{
        	  alert('패스워드가 일치하지 않습니다.')             
              $('#submit').attr('disabled','disabled');
          }
       }
    });
    
    
    $('#userid').blur(function(){
    	
    	 var userid = $('#userid').val();
    	    
    	    $.ajax({
    	    	
    	    	url:'idcheck',
    	    	type:'post',
    	    	dataType:'html',
    	    	data:{userid: userid},
    	    	success: function(result){
    	    		
    	    		if(result == "ok"){
    	    		
    	    			if(userid == ""){
        	    			alert('아이디를 입력해주세요');
        	    			$('#submit').attr('disabled','disabled');
        	    			
        	    		}else if(userid != ""){
    	    				alert('사용 가능한 아이디입니다.');
    	    				$('#submit').removeAttr('disabled');
    	    			}
    	    		}
    	    		
    	    		if(result == "error"){
    	    			
    	    			alert('중복되는 아이디입니다.');
    	    			$('#userid').val("");
    	    			$('#submit').attr('disabled','disabled');
    	    			
    	    		}
    	    		
    	    	}
    	    	
    	    });
    	
    	
    });
    
    
    $('#nickname').blur(function(){
    	
    	var nickname = $('#nickname').val();
    	
    	$.ajax({
    		
    		url:'nicknamech',
    		type:'post',
    		dataType:'html',
    		data:{nickname: nickname},
    		success: function(checker){
    			
    			if(checker == "0"){
    				
    				if(nickname != ""){
    					alert('사용 가능한 닉네임입니다.');
    					$('#submit').removeAttr('disabled');
    				}else if(nickname == ""){
    					alert('닉네임을 입력해주세요');
    					$('#submit').attr('disabled','disabled');
    				}
    				
    			}
    			
    			if(checker == "1"){
					alert('중복되는 닉네임입니다.');
					$('#nickname').val("");
					$('#submit').attr('disabled','disabled');
				}
    			
    		}
    		
    		
    	});
    	
    });
    
    
}

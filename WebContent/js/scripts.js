
jQuery(document).ready(function() {
	
    /*
        屏幕背景
    */
    $.backstretch("img/loginBg.jpg");
    
    /*
        登录表单验证
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	if ($('input[type="text"]').val().length!=0 && $('input[type="password"]').val().length!=0) {
				if($('input[type="text"]').val()=="admin" && $('input[type="password"]').val()=="admin"){
	                $(this).submit();
	                alert("登陆成功！");
	            }else{
	            	$('input[type="text"],input[type="password"]').addClass('input-error');
	            	e.preventDefault();
	              	alert("账号密码错误，请重新输入！");
	                return false;
	         	}
	    }else{
	    	    $('input[type="text"],input[type="password"]').addClass('input-error');
	    	    e.preventDefault();
	         	alert("输入不能为空，请重新输入！");
				return false;
	     }
    }); 
    
});

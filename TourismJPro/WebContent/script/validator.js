$().ready(function(){
	$("#registerform").validate({
		errorElement : "span",
		errorPlacement : function(error, element) {
			var errorplace = element.parent("dd").next().first("span");
			errorplace.text("");
			error.appendTo( errorplace );
		},
		submitHandler:function(form){
			form.submit();
        },
		rules: {
			name: {
				required:true,
				rangelength : [6,16]
			},
			mobile: {
				required: true,
				digits: true
			},
			email: {
				email: true
			},
			password: {
				required: true,
				rangelength:[6,12]
			},
			password2: {
				required: true,
				rangelength:[6,12],
				equalTo: "#password"
			}

		},
        messages: {
			name: {
				required: "<span class='validatorMsg validatorError'>请填写用户名</span>",
				rangelength : jQuery.format("<span class='validatorMsg validatorError'>{0}-{1}个字符（汉字、字母、数字、下划线）</span>")
			},
			mobile: {
				required: "<span class='validatorMsg validatorError'>请输入手机号码</span>",
				digits: "<span class='validatorMsg validatorError'>请输入手机号码</span>"
			},
			email: {
				email: "<span class='validatorMsg validatorError'>请输入正确的email地址</span>"
			},
			password: {
				required: "<span class='validatorMsg validatorError'>请输入密码</span>",
				rangelength: jQuery.format("<span class='validatorMsg validatorError'>{0}-{1}个字符</span>")
			},
			password2: {
				required: "<span class='validatorMsg validatorError'>请输入确认密码</span>",
				rangelength: "<span class='validatorMsg validatorError'>{0}-{1}个字符</span>",
				equalTo: "<span class='validatorMsg validatorError'>两次输入密码不一致</span>"
			}
		}
    });

});
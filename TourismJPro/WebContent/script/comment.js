var editor;
KindEditor.ready(function(K) {
  	 editor = K.create('#content', {
		 width: '798px',
		 height: '150px',
		 resizeType: 0,
  		 items: []
  	 });
});

function publish() {
	var content = editor.html();
	var id = $("#product_id").val();
	var time = getDate();
	if (content != "") {
		$.ajax({
			type: "post",
			url: "reply",
			data: {"content": content, "id": id},
			dataType: "json",
			success: function(data){
				if (data["success"]){
					var html = "<div class=\"name\"><div class=\"re-text\">&nbsp;&nbsp;&nbsp;网友：" + data["name"] + "</div></div>" +
								"<div class=\"content2\"><div><pre>" + content + "</pre></div>" +
								"</div><div class=\"time\"><span>评论时间：" + time + "&nbsp;</span></div>";
					$("#reply-list").prepend(html);
				} else {
					alert("请确定是否登录，是否购买已付款！否则不能发表评论!");					
				}
			}
		});
	}
}

function getDate() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var monthstr = "";
	if (month < 10) {
		monthstr = "0" + month;
	}
	var day = date.getDate();
	var daystr = "";
	if (day < 10) {
		daystr = "0" + day;
	}
	var time = year + "-" + monthstr + "-" + daystr;
	return time;
}





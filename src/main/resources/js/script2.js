function saveSubcriber(baseUrl) {
	// tạo javascript object.  
	var requestBody = {
		name: jQuery("#idName").val(),
		email: jQuery("#idEmail").val(), // lay theo id
		 // lay theo id
	};
	
	
	alert("Bắt đầu gọi AJAX");
	// $ === jQuery
	// json == javascript object
	jQuery.ajax({
		url: baseUrl + "/ajax/subcribe", //->request mapping
		type: "post",					   //-> method type của Request Mapping	
		contentType: "application/json",   //-> nội dung gửi lên dạng json <=> javascript object
		data: JSON.stringify(requestBody),

		dataType: "json", // kiểu dữ liệu trả về từ Controller
		success: function(jsonResult2) { // gọi ajax thành công
			alert(jsonResult2.statusMessage);
		},
		error: function(jqXhr, textStatus, errorMessage) { // gọi ajax thất bại
			alert("error");
		}
	});
}
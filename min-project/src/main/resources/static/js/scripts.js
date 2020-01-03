String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

// ajax를 통한 create
$(".submit-write input[type=submit]").click(addAnswer);

function addAnswer(e){
	e.preventDefault();
	var queryString = $(".submit-write").serialize();
	var url = $(".submit-write").attr('action');
	
	$.ajax({
			type : 'post',
			url : url,
			data : queryString,
			dataType : 'json',
			error : onError,
			success : onSuccess
	}); 
}

function onError(){
	alert('fail');
}

function onSuccess(data, status){
	console.log(data);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.user.userId, data.formattedDate, data.contents, data.question.idx, data.idx);
	
	$(".qna-comment-slipp-articles").prepend(template);
	$(".submit-write textarea").val("");
	
	$(".qna-comment-count strong").text(data.question.countOfAnswer);
}

//ajax를 통한 delete
$(document).on('click', '.link-delete-article', deleteAnswer);

function deleteAnswer(e){
	e.preventDefault();
	var url = $(this).attr('href');
	var deleteBtn = $(this);
	
	$.ajax({
		type : 'delete',
		url : url,
		dataType : 'json',
		error : function(xhr, status){
			console.log("error");
		},
		success : function(data, status){
			console.log("success");
			console.log(data);
			
			if(data.result.valid){
				deleteBtn.closest(".article").remove();
				$(".qna-comment-count strong").text(data.answer.question.countOfAnswer);
			}
			
		}
});
	
}
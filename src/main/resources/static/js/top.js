$(function() {
	$(".issuebox").attr("draggable",true)
	$(".issuebox").on("dragstart", onDragStart);
	$(".kptbox").on("dragover", onDragOver);
	$(".kptbox").on("drop", onDrop);


	function onDragStart(event) {
		event.originalEvent.dataTransfer.setData("text", this.class);
	}
	function onDragOver(event) {
		event.preventDefault();
	}

	function onDrop(event) {
		event.preventDefault();
		var id = event.originalEvent.dataTransfer.getData("text");
		console.log(id);
	}
	
	/*KPTBOX上でクリック→課題更新*/
	$('.issuebox').on('click', function() {
		  dispLoading("...");

		var issueKey = $(this).find('.issue-key').text()
		var url = '/issue/'+issueKey
		
		$.ajax({
			url:url,
			type:'GET'
		})
		.done((data) => {
			removeLoading();
			console.log('get issue info')
			createModalInfo(data);
		    $('#modalArea').fadeIn();
		})
		.fail((data) => {
			removeLoading();
			console.log('failure issue info')
		})
	})
	
	  $('#closeModal , #modalBg').click(function(){
		    $('#modalArea').fadeOut();
		    removeModalInfo();
	});
	/*何もない箇所でクリック→新規課題*/
	$('.kptbox').on('click', function() {
	    $('#modalArea').fadeIn();
	    $('#modalTitle').text('Regist Issue!')
	});
	
	  /* ------------------------------
	  Loading イメージ表示関数
	  引数： msg 画面に表示する文言
	  ------------------------------ */
	 function dispLoading(msg){
	   // 引数なし（メッセージなし）を許容
	   if( msg == undefined ){
	     msg = "";
	   }
	   // 画面表示メッセージ
	   var dispMsg = "<div class='loadingMsg'>" + msg + "</div>";
	   // ローディング画像が表示されていない場合のみ出力
	   if($("#loading").length == 0){
	     $("body").append("<div id='loading'>" + dispMsg + "</div>");
	   }
	 }
	  
	 /* ------------------------------
	  Loading イメージ削除関数
	  ------------------------------ */
	 function removeLoading(){
	   $("#loading").remove();
	 }
	 
	 /*
	  * サーバから取得した課題情報をフォームに反映させる。
	  */
	 function createModalInfo(data) {
		 $('#inputUser').html('<i class="fas fa-user"></i>&nbsp;'+data.createdUser.name)
	    $('#modalTitle').text('Update Issue!')
		 var form = $('#form-issue')
		 form.attr('action','/issue/'+data.issueKey);
		 $('#inputSummary').val(data.summary);
		 $('#inputStatus').removeAttr('disabled');
		 $('#inputDescription').val(data.description);
		 var statusId = data.status.id
		 var categoryId = data.category[0].id
		 $('#inputStatus option[value="'+statusId+'"]').attr('selected','selected')
		 $('#inputCategory option[value="'+categoryId+'"]').attr('selected','selected')
	 }
	 
	 /*
	  * フォームをリセットする。
	  */
	 function removeModalInfo() {
		    $('#modalTitle').text('')
		 $('#inputUser').text('');
		 var form = $('#form-issue')
		 form.attr('action','/issue');
		 $('#inputSummary').val('');
		 $('#inputDescription').val('');
		 $('#inputStatus').attr('disabled','disabled');
		 $('#inputStatus option').removeAttr('selected')
		 $('#inputCategory option').removeAttr('selected')
		 
	 }
	 
	  
});


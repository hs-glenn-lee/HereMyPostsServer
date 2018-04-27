<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/font/nanumgothic/font-nanumgothic.css">
<link rel="stylesheet" href="/css/font/nanumbrush/font-nanumbrush.css">
<script src='/js/tinymce/tinymce.min.js'></script>
 <script>
 tinymce.init({
	selector: '#content',
	language : 'ko_KR',
	height: 500,
	theme: 'modern',
	plugins: 'preview fullpage autolink visualblocks fullscreen image link media codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools  contextmenu colorpicker textpattern help',
	toolbar1: 'formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | fontselect fontsizeselect | removeformat',
	image_advtab: true,
	font_formats: '나눔손글씨=NanumBrush;나눔고딕=NanumGothic;Andale Mono=andale mono,times;Arial=arial,helvetica,sans-serif;Arial Black=arial black,avant garde;Book Antiqua=book antiqua,palatino;Comic Sans MS=comic sans ms,sans-serif;Courier New=courier new,courier;Georgia=georgia,palatino;Helvetica=helvetica;Impact=impact,chicago;Symbol=symbol;Tahoma=tahoma,arial,helvetica,sans-serif;Terminal=terminal,monaco;Times New Roman=times new roman,times;Trebuchet MS=trebuchet ms,geneva;Verdana=verdana,geneva;Webdings=webdings;Wingdings=wingdings,zapf dingbats',
	content_css: [
		'//localhost:8080/css/font/nanumgothic/font-nanumgothic.css',
		'//localhost:8080/css/font/nanumbrush/font-nanumbrush.css'
	],//iframe을 뚫기 때문에 content_css에 ifr안에 적용되는 css를 넣어야한다.
	
	images_upload_url: 'http://localhost:8080/article/resources/image',

	
	image_title: true, 
	// enable automatic uploads of images represented by blob or data URIs
	automatic_uploads: false,

	file_picker_types: 'image', 
	// and here's our custom image picker
	file_picker_callback: function(cb, value, meta) {
	var input = document.createElement('input');
	input.setAttribute('type', 'file');
	input.setAttribute('accept', 'image/*');
	  
	// Note: In modern browsers input[type="file"] is functional without 
	// even adding it to the DOM, but that might not be the case in some older
	// or quirky browsers like IE, so you might want to add it to the DOM
	// just in case, and visually hide it. And do not forget do remove it
	// once you do not need it anymore.
	
	input.onchange = function() {
	  var file = this.files[0];
	    
	  var reader = new FileReader();
	  reader.onload = function () {
	    // Note: Now we need to register the blob in TinyMCEs image blob
	    // registry. In the next release this part hopefully won't be
	    // necessary, as we are looking to handle it internally.
	    var id = 'blobid' + (new Date()).getTime();
	    var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
	    var base64 = reader.result.split(',')[1];
	    var blobInfo = blobCache.create(id, file, base64);
	    blobCache.add(blobInfo);
	
	    // call the callback and populate the Title field with the file name
	    cb(blobInfo.blobUri(), { title: file.name });
	  };
	  reader.readAsDataURL(file);
	};
	  
	input.click();
	}
 });
 </script>
</head>
<body>

  
  <main>
  
  	<p style="font-family: NanumBrush;">나눔손글씨</p>
	<form id="write-article-form" action="write" method="post">
  		<div class="input-item">
  			<label class="input-label">title</label>
  			<input name="title" type="text"/>
  			<span class="input-description"></span>
  		</div>
  		<div class="input-item">
  			<label class="input-label">categoryId</label>
  			<input name="categoryId" type="text"/>
  			<span class="input-description"></span>
  		</div>
  		<div class="input-item">
  			<label class="input-label">content</label>
  			<input name="content" type="text"/>
  			<span class="input-description"></span>
  		</div>
  		<textarea id="content"></textarea>
		<input type="submit" value="sign-up"/>
  	</form>
  </main>

</body>
</html>
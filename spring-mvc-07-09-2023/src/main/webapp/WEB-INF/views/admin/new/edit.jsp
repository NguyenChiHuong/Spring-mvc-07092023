<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib/taglib.jsp" %>
<c:url var="newURL" value="/quan-tri/bai-viet/danh-sach" />
<c:url var="editNewURL" value="/quan-tri/bai-viet/chinh-sua" />
<c:url var="newAPI" value="/api/new" />
<!DOCTYPE html>
<html>
<head>
	<title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">Home</a>
				</li>
				<li>
					<a href="#">Forms</a>
				</li>
				<li class="active">Form Elements</li>
			</ul><!-- /.breadcrumb -->
		</div>
		<div class="page-content">	 	
			<div class="row">
				<div class="col-xs-12">
					<c:if test="${not empty message}">
						<div class="alert alert-${alert}">
							${message}
						</div>
					</c:if>
					<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model" enctype="multipart/form-data">
						<div class="form-group">
						    <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Thể loại</label>
						    <div class="col-sm-9">
						    	 <form:select path="categoryCode"  id="categoryCode" > <!--path={type + name}  -->
						    	 	  <form:option value="" label="--Chọn thể loại--"/>
								      <form:options items="${categories}"/>
						    	</form:select>
						    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="title">Tên bài viết</label>
							<div class="col-sm-9">
								<form:input path="title" cssClass="col-xs-10 col-sm-20" id="title" />
							</div>
						</div>
						<div class="form-group">
							 <label for="thumbnail" class="col-sm-3 control-label no-padding-right">Ảnh đại diện</label>
							 <div class="col-sm-9">
							  	 <input type="file" class="form-control-file" id="thumbnail" name="thumbnail" multiple="multiple">
							 </div>
						</div>
						<div class="form-group">
							 <label for="shortDescription" class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
							 <div class="col-sm-9">
						      	  <form:textarea cssClass="form-control" path="shortDescription" rows="5" cols="10" id="shortDescription"/>
							 </div>
						</div>
						<div class="form-group">
							 <label for="content" class="col-sm-3 control-label no-padding-right">Nội dung</label>
							 <div class="col-sm-9">					
						     	 <form:textarea cssClass="form-control" path="content" rows="5" cols="10" id="content"/>
							 </div>	  
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<form:hidden path="id" id="newId"/> 
								<c:if test="${not empty model.id}">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Cập nhật bài viết
									</button>
								</c:if>
								<c:if test="${empty model.id}">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Thêm bài viết
									</button>
								</c:if>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset" id="">
									<i class="ace-icon fa fa-undo bigger-110"></i>
									Hủy bỏ
								</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div> 
	</div> 
</div>
<script type="text/javascript">
var editorContent = '';
var editorShortDescription = '';

$(document).ready(function(){
    editorContent = CKEDITOR.replace('content');
    editorShortDescription = CKEDITOR.replace('shortDescription');
});

var data = {};

$("#thumbnail").change(function(){
	var files = $(this)[0].files[0];
	if(files){
		var reader = new FileReader();
		reader.onload = function(e){
			data["thumbnailBase64"] = e.target.result;
			data["thumbnail"] = files.name;
		};
		reader.readAsDataURL(files);
	}
});

$('#btnAddOrUpdateNew').click(function (e) {
    e.preventDefault();
    var formData = $('#formSubmit').serializeArray();
    $.each(formData, function (i, v) {
        data[""+v.name+""] = v.value;
    });
    data["content"] = editorContent.getData();
    data["shortDescription"] = editorShortDescription.getData();
    var id = $('#newId').val();
    if(id == ""){
		createNew(data);
    }else{
        updateNew(data);
    }
});

function createNew(data){
	$.ajax({
        url: '${newAPI}',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (result) {
        	window.location.href = "${editNewURL}?id="+result.id+"&message=insert_success";
        },
        error: function (error) {
        	window.location.href = "${editNewURL}?page=1&limit=2&message=error_system";
        }
    });
}

function updateNew(data){
	$.ajax({
        url: '${newAPI}',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (result) {
        	window.location.href = "${editNewURL}?id="+result.id+"&message=update_success";
        },
        error: function (error) {
        	window.location.href = "${editNewURL}?id="+result.id+"&message=error_system";
        }
    });
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script src="js/star-rating.min.js" type="text/javascript"></script>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/star-rating.min.css" media="all" rel="stylesheet"
	type="text/css" />

<title>Ajouter une evaluation</title>
</head>
<body>
	<a href="<s:url action='formAddEval' />">Ajouter une evaluation</a>

	<!-- <legend>Evaluation</legend> -->

	<div class="row">
		<div class="col-lg-offset-3 col-lg-6 col-sm-offset-3 col-sm-6">
			<section class="content-wrapper main-content clear-fix">
				<form id="addEval" class="form-horizontal" method="post" 
										action="<s:url action='addEval'/>">

					<s:iterator value="lstCriteresEval" status="idx">

						<label class="col-lg-4 control-label"> <s:property
								value="libcourt" />
						</label>
						<input type="number" id='"<s:property value="libcourt" />" '  
						 class="rating" min=0 max=5 step=1
							data-size="sm" data-rtl="false" />

					</s:iterator>

					<input type="hidden" name="idBar" id="ididBar" value="1" /> <input
						type="hidden" name="idLib" id="idLib" />

					<fieldset>
						<div class="form-group">
							<div class="col-lg-4">
								<div class="row">
									<div class="col-lg-6">
										<br> <br> <label for="idCommentaire"
											class="col-lg-4 control-label">Commentaire</label> 
											<input
											class="form-control" id="idCommentaire" name="commentaire" />
									</div>
									<div class="col-lg-6">&nbsp;</div>
								</div>
							</div>
						</div>
					</fieldset>

					<div class="form-group">
						<div class="col-lg-4 col-lg-offset-2 col-sm-4 col-sm-offset-2">
							<button type="reset" class="btn btn-default btn-center">
								<span class="glyphicon glyphicon-refresh"></span> État initial
							</button>
						</div>
						<div class="col-lg-4">
							<button type="submit" id="test"
								class="btn btn-primary btn-center">
								<span class="glyphicon glyphicon-ok"></span> Mettre à jour
							</button>
						</div>
					</div>
				</form>
			</section>
		</div>
	</div>

	<script>
	
		$(function() {
			$('#addEval').submit(function() {
				var sz = "";
				$.each($('.rating'), function(index, elt) {
					sz += $(elt).val() + ',';
				});
				$('#idLib').val(sz);
			});
		});
		
	</script>
</body>
</html>
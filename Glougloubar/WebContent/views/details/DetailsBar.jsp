<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet">
<title>Détails bar</title>
</head>
<body>
	<div class="row" style="margin-top:100px;">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="container">
				<div class="jumbotron">
					<h1>Détails bar</h1>
					<s:if test="%{leBar.lstHoraires.size > 0}">						
						<table class="table table-striped table-hover">					
							<thead>
								<tr>
									<th>Jour</th>
									<th>Heures d'ouverture</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="leBar.lstHoraires">
									<tr>
										<td><s:property value="getNomJour()" /></td>
										<td>
											<s:property value="heureDebut.getHours()"/>h<s:property value="heureDebut.getMinutes()"/>
										  - <s:property value="heureFin.getHours()"/>h<s:property value="heureFin.getMinutes()"/></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:if>
					<a href="<s:url action='retourIndex' />">Retour à l'index</a>
					<a href="<s:url action='formAddEval' />">Ajouter Evaluation</a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-2"></div>
</body>
</html>
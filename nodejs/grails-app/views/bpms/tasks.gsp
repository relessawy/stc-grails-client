<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Red Hat BPMS</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        
    </content>

	 <div id="content" role="main">
    		<div class="row">
    		<div class="col-sm-offset-1 col-sm-8">
    		
    		<div class="panel panel-info">
    		<div class="panel-heading">Tasks</div>
    		
    		<div class="panel-body"> 	
        	<table class="table dataTable">
        	<thead>
        	<tr>
        		<th>name</th><th>created On</th>
	        	<th>status</th><th>activated On</th>
	        	<th>owner</th>
	        	<th></th>
        	</tr>
        	</thead>
        	<tbody>
        	<g:each var="taskInstance" in="${tasks}" status="index">
        		<tr><td>${taskInstance?.name}</td>
        			<td>${taskInstance?.createdOn}</td>
        			<td>${taskInstance?.status}</td>
        			<td>${taskInstance?.activatedOn}</td>
        			<td>${taskInstance.owner}</td>
        			<td>
        			<g:if test="${taskInstance?.status=='Ready'}">
        				<g:link class="btn btn-success" action="claim" id="${taskInstance?.id}"> Claim </g:link>
        			</g:if>
        			<g:elseif test="${taskInstance?.status=='InProgress'}">
        				<g:link class="btn btn-success" action="release" id="${taskInstance?.id}"> Rlease </g:link>
        			</g:elseif>
        			</td>
        		</tr> 
        	</g:each>
        	</tbody>
        	</table>
        	</div>
        	
        	</div>
        	</div>
        	</div>
    </div>
    

</body>
</html>

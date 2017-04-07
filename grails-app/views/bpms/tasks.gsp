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
            <th>id</th>
        		<th>name</th><th>created On</th>
	        	<th>status</th><th>activated On</th>
	        	<th>owner</th>
	        	<th></th>
        	</tr>
        	</thead>
        	<tbody>
        	<g:each var="taskInstance" in="${tasks}" status="index">
        		<tr>
              <td>${taskInstance?.id}</td>
              <td>${taskInstance?.name}</td>
              <td>${(new java.text.SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSXXX")).parse(taskInstance?.createdOn).format("yyyy-MM-dd hh:mm:ss")}</td>
        			<td>${taskInstance?.status}</td>
              <td>${(new java.text.SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSXXX")).parse(taskInstance?.activatedOn).format("yyyy-MM-dd hh:mm:ss")}</td>
        			<td>${taskInstance.owner}</td>
        			<td>
        			<g:if test="${taskInstance?.status=='Ready'}">
        				<g:link class="btn btn-success" action="claimTask" id="${taskInstance?.id}" params="${params}"> Claim </g:link>
        			</g:if>
        			<g:elseif test="${taskInstance?.status in ['InProgress','Reserved']}">
        				<g:link class="btn btn-success" action="releaseTask" id="${taskInstance?.id}" params="${params}"> Release </g:link>
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

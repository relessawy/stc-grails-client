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
    		<div class="panel-heading">Process Definition</div>

    		<div class="panel-body">
        	<table class="table dataTable">
        	<thead>
        	<tr>
        		<th>name</th><th>description</th>
	        	<th>status</th><th>start</th>
	        	<th>tasks</th>
        	</tr>
        	</thead>
        	 <tbody>
        	<g:each var="processInstance" in="${instances}" status="index">
        		<tr><td>${processInstance.name}</td>
        			<td>${processInstance.description}</td>
        			<td>${processInstance.getStatus()}</td>
              <td>${(new java.text.SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSXXX")).parse(processInstance.start).format("yyyy-MM-dd hh:mm:ss")}</td>
        			<td><g:link action="tasks" params="[processInstanceId : processInstance.id]" class="btn btn-info"> show </g:link> </td>
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

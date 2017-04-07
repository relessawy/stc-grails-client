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
        	<table class="table">
        	<tr><th>Process</th><th>Action</th></tr>
        	<g:each var="process" in="${processes}" status="index">
        		<tr><td>${process.name}</td>
        			<td>
        			<g:link action="start" params="['process':process.id,'deploymentId':process.deployment.id]" class="btn btn-success"> start </g:link>
        			<g:link action="instances" params="[processId : process.id]" class="btn btn-info"> instances </g:link>
        			</td>
        		</tr>
        	</g:each>
        	</table>
        	</div>

        	</div>
        	</div>
        	</div>
    </div>


</body>
</html>

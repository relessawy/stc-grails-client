<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        
    </content>

    <div id="content" role="main">
    		<div class="row">
    		<div class="col-sm-offset-1 col-sm-8">
    		
    		<div class="panel panel-info">
    		<div class="panel-heading">Process deployments</div>
    		
    		<div class="panel-body">    		
        	<table class="table">
        	<tr><th>deployment</th><th>Action</th></tr>
        	<g:each var="deployment" in="${deployments}" status="index">
        		<tr><td>${deployment.artifactId}</td>
        			<td><g:link action="processes" params="[deploymentId : deployment.id]" class="btn btn-info" > 
        			Processes </g:link> </td>
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

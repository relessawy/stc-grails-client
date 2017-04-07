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
        <g:form name="myForm">

    		<div class="panel panel-info">
    		<div class="panel-heading"><g:message code="salary.deduction.confirm"/>
          <span class="pull-right">
          <g:link action="start" params="[lang:'en',deploymentId:params.deploymentId,process:params.process]" class="btn btn-info"> <g:message code="request.new"/> </g:link>
          </span>
        </div>
    		<div class="panel-body">

        	<table class="table">
        	<tr>
            <th>Deployment</th><th>Process</th>
          </tr>
          <tr>
            <td>${deploymentId}</td><td>${process}</td>
          </tr>
        	</table>

          <g:hiddenField name="process" value="${params.process}" />
          <g:hiddenField name="deploymentId" value="${params.deploymentId}" />
          <g:hiddenField name="lang" value="${params.lang}" />
          <table class="table">
        	<tr>
            <th><g:message code="employee.id"/></th><td>${params.employeeId}</td>
          </tr>
          <tr>
            <th><g:message code="employee.name"/></th><td>${params.employeeName}</td>
          </tr>
          <tr>
            <th><g:message code="deduction.type"/></th><td>${params.deductionType}</td>
          </tr>
          <tr>
            <th><g:message code="deduction.amount"/></th><td>${params.amount}</td>
          </tr>
        	</table>

        	</div>
        	</div>

        </g:form>

        	</div>
        	</div>
    </div>


</body>
</html>

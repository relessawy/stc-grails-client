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
        <g:form name="myForm" action="confirm">

    		<div class="panel panel-info">
    		<div class="panel-heading"><g:message code="salary.deduction"/>
          <span class="pull-right">
          <g:link action="start" params="[lang:'en',deploymentId:params.deploymentId,process:params.process]" class="btn btn-info"> en </g:link>
          <g:link action="start" params="[lang:'ar',deploymentId:params.deploymentId,process:params.process]" class="btn btn-info"> عربي </g:link>
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
            <th><g:message code="employee.id"/></th><td><input size="5" name="employeeId" required/></td>
          </tr>
          <tr>
            <th><g:message code="employee.name"/></th><td><input size="20" name="employeeName" required/></td>
          </tr>
          <tr>
            <th><g:message code="deduction.type"/></th><td><select id="deductionType" name="deductionType"><option>Donation</option><option>Stop Donation</option><option>Fitness Time</option></select>
              <div id="deductionTypeDiv"></div>
            </td>
          </tr>
          <tr>
            <th><g:message code="donate.to"/></th><td>
              <select id="donateTo" class="donateTo" name="donateTo"><option>Social Welfare Program</option><option>Legam com</option><option>Syrian Children Support</option>
              </select>
              <div id="donateToDiv"></div>
            </td>
          </tr>
          <tr>
            <th><g:message code="deduction.amount"/></th><td><input size="10" name="amount" required></td>
          </tr>

        	</table>
          <g:actionSubmit value="${message(code:'send.request')}" action="confirm" class="btn btn-success" />
        	</div>
        	</div>

        </g:form>

        	</div>
        	</div>
    </div>
<script type="text/javascript">
window.onload = function(e){
  $("#donateTo").change(function () {
    if($(this).val()=="Syrian Children Support")
      $("#donateToDiv").text("<g:message code="donation.syrian"/>");
    else
      $("#donateToDiv").text("");
  });

  $("#deductionType").change(function () {
    if($(this).val()=="Stop Donation")
      $("#deductionTypeDiv").text("<g:message code="stop.donation"/>");
    else
      $("#deductionTypeDiv").text("");
  });
}
</script>

</body>
</html>

package restclient

import grails.transaction.Transactional

@Transactional
class BpmsService {
	//String authenticationToken = "hank:P@ssw0rd"
	String authenticationToken = "employee1:P@ssw0rd"
	String baseURL = "http://127.0.0.1:8080/business-central"

	 def deployments() {
    	def rest = new grails.plugins.rest.client.RestBuilder()
	    	String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
    	def resp = rest.get("${baseURL}/rest/deployment"){
            auth encoded
        }
        List<Deployment> list = new ArrayList<Deployment>()
        resp.xml.childNodes().each{deploymentNode->
        	def groupId= deploymentNode.childNodes().find{it.name()=='groupId'}.text()
        	def artifactId= deploymentNode.childNodes().find{it.name()=='artifactId'}.text()
        	def status= deploymentNode.childNodes().find{it.name()=='status'}.text()
        	def version= deploymentNode.childNodes().find{it.name()=='version'}.text()
        	list.add(new Deployment(groupId,artifactId,status,version))
        }
        return list
     }


    def processes(deploymentId) {
    	def rest = new grails.plugins.rest.client.RestBuilder()
    	String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
    	def resp = rest.get("${baseURL}/rest/deployment/${deploymentId?deploymentId+'/':''}processes"){
            auth encoded
        }
    	List<ProcessDefinition> list = new ArrayList<ProcessDefinition>()
        resp.xml.childNodes().each{processDefinitionNode->
        	def id= processDefinitionNode.childNodes().find{it.name()=='id'}.text()
        	def name= processDefinitionNode.childNodes().find{it.name()=='name'}.text()
        	def version= processDefinitionNode.childNodes().find{it.name()=='version'}.text()
					def deplyment = new Deployment(processDefinitionNode.childNodes().find{it.name()=='deployment-id'}.text())
        	list.add(new ProcessDefinition(id,name,version,deplyment))
        }
        return list

				/*
					<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
					<process-definition-list>
					<process-definition>
					<id>deduction.SalaryDeduction</id><name>SalaryDeduction</name><version>1.0</version><package-name>org.jbpm</package-name>
					<deployment-id>deduction:deduction:1.0</deployment-id>
					<forms/>
					<variables>
					<entry><key>request</key><value>deduction.DeductionRequest</value></entry>
					<entry><key>wsErrorStack</key><value>String</value></entry>
					<entry><key>wsError</key><value>WorkItemHandlerRuntimeException</value></entry>
					<entry><key>vacation</key><value>deduction.Vacation</value></entry>
					<entry><key>employee</key><value>deduction.Employee</value></entry>
					<entry><key>ruleValidation</key><value>deduction.RuleValidation</value></entry>
					<entry><key>requestor</key><value>String</value></entry>
					</variables>
					</process-definition>
					<process-definition><id>deduction.SalaryDeductionRest</id><name>SalaryDeductionRest</name><version>1.0</version><package-name>org.jbpm</package-name><deployment-id>deduction:deduction:1.0</deployment-id><forms/><variables><entry><key>request</key><value>deduction.DeductionRequest</value></entry><entry><key>wsErrorStack</key><value>String</value></entry><entry><key>wsError</key><value>WorkItemHandlerRuntimeException</value></entry><entry><key>vacation</key><value>deduction.Vacation</value></entry><entry><key>employee</key><value>deduction.Employee</value></entry><entry><key>ruleValidation</key><value>deduction.RuleValidation</value></entry></variables></process-definition></process-definition-list>
				*/

     }

     def instances(processId) {
    	def rest = new grails.plugins.rest.client.RestBuilder()
    	String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
    	def resp = rest.get("${baseURL}/rest/history/"+ (processId?"process/"+processId:"instances")){
            auth encoded
        }

    	List<ProcessInstance> list = new ArrayList<ProcessInstance>()
        resp.xml.childNodes().each{processInstanceNode->
        	def id= processInstanceNode.childNodes().find{it.name()=='process-instance-id'}.text()
        	def name= processInstanceNode.childNodes().find{it.name()=='process-name'}.text()
        	def status= processInstanceNode.childNodes().find{it.name()=='status'}.text()
        	def start= processInstanceNode.childNodes().find{it.name()=='start'}.text()
        	def instanceProcessId = processInstanceNode.childNodes().find{it.name()=='external-id'}.text()
        	def description= processInstanceNode.childNodes().find{it.name()=='process-instance-description'}.text()
        	list.add(new ProcessInstance(id,name,status,start,instanceProcessId,description))

        }
        return list
     }

      def tasks(processInstanceId,user) {
    		def rest = new grails.plugins.rest.client.RestBuilder()
    		String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
				if(user)
					encoded= "Basic " + (user + ":P@ssw0rd").bytes.encodeBase64().toString()
    		def resp = rest.get("${baseURL}/rest/task/query"+ (processInstanceId?"?processInstanceId="+processInstanceId:"")){
            auth encoded
      	}

    		List<Task> list = new ArrayList<Task>()
        resp.xml.childNodes().each{taskNode->
        	def id= taskNode.childNodes().find{it.name()=='id'}?.text()
        	def name= taskNode.childNodes().find{it.name()=='name'}?.text()
        	def status= taskNode.childNodes().find{it.name()=='status'}?.text()
        	def createdOn= taskNode.childNodes().find{it.name()=='created-on'}?.text()
        	def activatedOn = taskNode.childNodes().find{it.name()=='activation-time'}?.text()
        	def owner = taskNode.childNodes().find{it.name()=='actual-owner'}?.text()
        	//def processInstanceId= taskNode.childNodes().find{it.name()=='process-instance-id'}?.text()
        	list.add(new Task(id,name,status,createdOn,activatedOn,processInstanceId,owner))

        }
        return list
     }

		 def task(id){

		 // process with variables
		 //http://localhost:8080/business-central/rest/runtime/deduction:deduction:1.0/withvars/process/instance/1
		 /*
		 <?xml version="1.0" encoding="UTF-8"?>
			<task>
			   <id>1</id>
			   <priority>0</priority>
			   <name>Approve</name>
			   <subject />
			   <description />
			   <names>
			      <id>2</id>
			      <text>Approve</text>
			      <language>en-UK</language>
			   </names>
			   <subjects>
			      <id>3</id>
			      <text />
			      <language>en-UK</language>
			   </subjects>
			   <descriptions>
			      <id>1</id>
			      <text />
			      <language>en-UK</language>
			   </descriptions>
			   <people-assignments>
			      <potential-owners>
			         <id>manager</id>
			         <type>GROUP</type>
			      </potential-owners>
			      <business-administrators>
			         <id>Administrator</id>
			         <type>USER</type>
			      </business-administrators>
			      <business-administrators>
			         <id>Administrators</id>
			         <type>GROUP</type>
			      </business-administrators>
			   </people-assignments>
			   <subTasksStrategy>NoAction</subTasksStrategy>
			   <taskData>
			      <status>Reserved</status>
			      <previousStatus>Ready</previousStatus>
			      <actual-owner>manager1</actual-owner>
			      <created-on>2017-04-06T16:17:32.645+04:00</created-on>
			      <activation-time>2017-04-06T16:17:32.645+04:00</activation-time>
			      <skipable>true</skipable>
			      <work-item-id>2</work-item-id>
			      <process-instance-id>1</process-instance-id>
			      <document-type>java.util.HashMap</document-type>
			      <document-access-type>Inline</document-access-type>
			      <document-content-id>1</document-content-id>
			      <output-content-id>-1</output-content-id>
			      <fault-content-id>-1</fault-content-id>
			      <parent-id>-1</parent-id>
			      <process-id>deduction.SalaryDeductionRest</process-id>
			      <process-session-id>2</process-session-id>
			      <deployment-id>deduction:deduction:1.0</deployment-id>
			   </taskData>
			   <deadlines />
			   <form-name>ApproveRequest</form-name>
			</task>
		 */

		 }

		 def releaseTask(id,user){
			 def rest = new grails.plugins.rest.client.RestBuilder()
			 String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
			 if(user)
				 encoded= "Basic " + (user + ":P@ssw0rd").bytes.encodeBase64().toString()
			 def resp = rest.post("${baseURL}/rest/task/"+id+"/release"){
					 auth encoded
			 }
			 //<?xml version="1.0" encoding="UTF-8" standalone="yes"?><response><status>SUCCESS</status><url>/business-central/rest/task/1/claim</url></response>
			 //def status = resp.xml.childNodes().find{it.name()=='response'}.childNodes().find{it.name()=='status'}.text()
			 def status = resp.xml.first().childNodes()[0].text()
			 return status
		 }

		 def claimTask(id,user){
			 def rest = new grails.plugins.rest.client.RestBuilder()
			 String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
			 if(user)
				 encoded= "Basic " + (user + ":P@ssw0rd").bytes.encodeBase64().toString()
			 def resp = rest.post("${baseURL}/rest/task/"+id+"/claim"){
					 auth encoded
			 }
			 def status = resp.xml.first().childNodes()[0].text()
			 return status
		 }

		 def startTask(id,user){
			 def rest = new grails.plugins.rest.client.RestBuilder()
			 String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
			 if(user)
				 encoded= "Basic " + (user + ":P@ssw0rd").bytes.encodeBase64().toString()
			 def resp = rest.post("${baseURL}/rest/task/"+id+"/start"){
					 auth encoded
			 }
			 def status = resp.xml.first().childNodes()[0].text()
			 return status
		 }

		 def stopTask(id,user){
			 def rest = new grails.plugins.rest.client.RestBuilder()
			 String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
			 if(user)
				 encoded= "Basic " + (user + ":P@ssw0rd").bytes.encodeBase64().toString()
			 def resp = rest.post("${baseURL}/rest/task/"+id+"/stop"){
					 auth encoded
			 }
			 def status = resp.xml.first().childNodes()[0].text()
			 return status
		 }

		 def processStartForm(){
			def rest = new grails.plugins.rest.client.RestBuilder()
			String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
			def resp = rest.get("${baseURL}/rest/runtime/deduction:deduction:1.0/process/deduction.SalaryDeduction/startform"){
				 auth encoded
			}
			System.out.println(resp.body)
			return "status"
		 }

		 def startProcess(language,employeeId,employeeName,deductionType,amount,deployment,definition){
			def rest = new grails.plugins.rest.client.RestBuilder()
			String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
			def resp = rest.post("${baseURL}/rest/runtime/${deployment}/process/${definition}/start?map_employeeId=${employeeId}&map_employeeName=${employeeName}&map_amount=${amount}&map_language=${language}&map_deductionType=${deductionType}"){
				 auth encoded
			}
			System.out.println(resp.xml)
			//def status = resp.xml.first().childNodes()[0].text()
			return "status"
		 }

}

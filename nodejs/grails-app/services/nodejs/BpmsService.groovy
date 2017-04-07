package nodejs

import grails.transaction.Transactional

@Transactional
class BpmsService {
	String authenticationToken = "hank:P@ssw0rd"	
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
        	list.add(new ProcessDefinition(id,name,version))
        }
        return list
     }
     
     def instances(processId) { 
     	print "${baseURL}/rest/history/"+ (processId?"process/"+processId:"instances")
     	
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
     
      def tasks(processInstanceId) { 
    	def rest = new grails.plugins.rest.client.RestBuilder()
    	String encoded = "Basic " + authenticationToken.bytes.encodeBase64().toString()
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
}

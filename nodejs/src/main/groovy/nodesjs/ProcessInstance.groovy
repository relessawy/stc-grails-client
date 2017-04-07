package nodejs

class ProcessInstance implements java.io.Serializable{
	String id
	String name
	String status
	String start
	String processId
	String description
		
	ProcessDefinition processDefinition
	
	ProcessInstance(id,name,status,start,processId,description){
		this.id=id
		this.name=name
		this.status=status
		this.start=start
		this.processId=processId
		this.description=description
	} 
	
	String getStatus(){
		if(status=="1")
			"active"
		else if(status=="3")
			"aborted"
	}
}

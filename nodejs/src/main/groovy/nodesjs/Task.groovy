package nodejs

class Task implements java.io.Serializable{
	String id
	String name
	String status
	String createdOn
	String activatedOn
	String owner

		
	ProcessInstance processInstance
	
	
	Task(id,name,status,createdOn,activatedOn,processInstanceId,owner){
		this.id=id
		this.name=name
		this.status=status
		this.createdOn=createdOn
		this.activatedOn=activatedOn
		this.owner=owner
		//processInstanceId
	} 
	
}

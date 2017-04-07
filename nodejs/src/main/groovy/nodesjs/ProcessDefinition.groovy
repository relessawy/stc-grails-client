package nodejs

class ProcessDefinition implements java.io.Serializable{
	String id
	String name
	String version
	String packageName
	
	Deployment deployment
	
	ProcessDefinition(id,name,version){
		this.id=id
		this.name=name
		this.version=version
	} 
}

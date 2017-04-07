package restclient

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

	ProcessDefinition(id,name,version,deployment){
		this.id=id
		this.name=name
		this.version=version
		this.deployment = deployment
	}
}

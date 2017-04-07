package restclient

class Deployment implements java.io.Serializable{
	String id
	String groupId
	String artifactId
	String version
	String status

	List<ProcessDefinition> processDefinitionList;

	Deployment(id){
		this.id = id
	}
	Deployment(groupId,artifactId,status,version){
		this.groupId=groupId
		this.artifactId=artifactId
		this.status=status
		this.version=version
		id = "${groupId}:${artifactId}:${version}"
	}
}

package nodejs

class BpmsController {
	def bpmsService
	
    def index() { 
    }
    
    def deployments() {     	
    	[deployments : bpmsService.deployments()]
    }
    
    def processes() {     	
    	[processes : bpmsService.processes(params?.deploymentId)]
    }
    def instances() {
        [instances : bpmsService.instances(params?.processId)]
    }
    
     def tasks() {
        [tasks : bpmsService.tasks(params?.processInstanceId)]
    }
}

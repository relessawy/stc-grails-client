package restclient

class BpmsController {
	def bpmsService

    def index() {
    }

		def start() {
				[process : params.process, deploymentId:params.deploymentId]
    }

		def confirm(){
			if (params.employeeId) {
				bpmsService.startProcess(params.lang,params.employeeId,params.employeeName,params.deductionType,params.amount,params.deploymentId,params.process)
			}
			[process : params.process, deploymentId:params.deploymentId]
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
        [tasks : bpmsService.tasks(params?.processInstanceId,params.user)]
    }

		def task() {
        [tasks : bpmsService.tasks(params?.processInstanceId)]
    }

		def claimTask() {
			 bpmsService.claimTask(params?.id,params.user)
			 redirect action:'tasks', params:params
	  }

		def releaseTask() {
			 def status = bpmsService.releaseTask(params?.id,params.user)
			 redirect action:'tasks', params:params
	  }

		def startTask() {
			 def status = bpmsService.startTask(params?.id,params.user)
			 redirect action:'tasks', params:params
	  }

		def stopTask() {
			 [status : bpmsService.stopTask(params?.id,params.user)]
			 redirect action:'tasks', params:params
	  }
}

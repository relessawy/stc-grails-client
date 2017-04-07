package restclient
import grails.converters.JSON
import grails.converters.XML

class EmployeeController {
  def employee1 = [id:1, name:'Ali' , salary:1000, grade : 'A']
  //def employee1 = [id:'1', firstName:'Ali',lastName:'Salim',hours:1800]
  def employee2 = [id:2, name:'Sami', salary:1000, grade : 'B']
  def employee3 = [id:3, name:'Nagi', salary:1000, grade : 'C']
  def employee4 = [id:4, name:'Ragi', salary:1000, grade : 'D']

  def employeeList = ['1':employee1,'2':employee2,'3':employee3,'4':employee4]
  def index() {
      print params
      def result = employeeList.get(params.id)
      render result as JSON

      //render employee1 as XML
      /*render(contentType: "application/json") {
        employee(id: result["id"], salary: result["salary"])
      }*/
  }

}

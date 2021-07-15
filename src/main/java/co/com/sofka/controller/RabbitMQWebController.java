package co.com.sofka.controller;

import co.com.sofka.modelo.Employee;
import co.com.sofka.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // indica que los datos devueltos por cada método se escribirán directamente en el cuerpo de la
// respuesta en lugar de representar una plantilla.
@RequestMapping(value = "/ javainuse-rabbitmq /") // la anotación se utiliza para asignar solicitudes web a los métodos
// de Spring Controller.
public class RabbitMQWebController {
    @Autowired // permite inyectar unas dependencias con otras dentro de Spring .
    RabbitMQSender rabbitMQSender;

    @GetMapping(value = "/producer") //GetMapping =  para mapear solicitudes HTTP GET en métodos de controlador específicos.
    public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) { // @RequestParam
        // para extraer parámetros de consulta, parámetros de formulario e incluso archivos de la solicitud.

        Employee emp = new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);
        rabbitMQSender.send(emp);

        return "Mensaje enviado a RabbitMQ JavaInUse con éxito";
    }
}

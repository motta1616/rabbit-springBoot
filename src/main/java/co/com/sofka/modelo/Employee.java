package co.com.sofka.modelo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

// @JsonIdentityInfo se usa cuando los objetos tienen una relación padre-hijo. @JsonIdentityInfo se usa para indicar
// que la identidad del objeto se usará durante la serialización / deserialización.

// ObjectIdGenerators.IntSequenceGenerator = Generador simple basado en números de secuencia, que utiliza Java ints básico (comenzando con el valor 1) como identificadores de objetos.
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Employee.class)
public class Employee {
    private String empName;
    private String empId;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Employee [empName=" + empName + ", empId=" + empId + "]";
    }
}

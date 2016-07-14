
package bmpapp;

public class EmployeePK implements java.io.Serializable {
  public Integer empNo;
  public String empName;
  public Float salary;

  public EmployeePK(Integer empNo) {
    this.empNo = empNo;
    this.empName = null;
    this.salary = null;
  }

  public EmployeePK(Integer empNo, String empName, Float salary) {
    this.empNo = empNo;
    this.empName = empName;
    this.salary = salary;
  }
  public EmployeePK(){}
  
}
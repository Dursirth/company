package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

public class CompanyImpl implements Company {
    private Employee[] employees;
    private int size;

    public CompanyImpl(int capacity) {
       employees = new Employee[capacity];
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if(employee == null || size == employees.length || findEmployee(employee.getId()) != null) {
            return false;
        }
        employees[size++] = employee;
        return true;
    }

    @Override
    public Employee removeEmployee(int id) {
        Employee victim = null;
        for (int i = 0; i < size; i++) {
            if(employees[i].getId() == id) {
                victim = employees[i];
                System.arraycopy(employees,i + 1,employees,i,size - i - 1);
                employees[--size] = null;
                break;
            }
        }
        return victim;
    }

    @Override
    public Employee findEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if(employees[i].getId() == id){
                return employees[i];
            }
        }
        return null;
    }

    @Override
    public int quantity() {
        return size;
    }

    @Override
    public double totalSalary() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += employees[i].calcSalary();
        }
        return sum;
    }

    @Override
    public double avgSalary() {
        return totalSalary() / size;
    }

    @Override
    public double totalSales() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i] instanceof SalesManager sm) {
                sum += sm.getSalesValue();
            }
        }
        return sum;
    }

    @Override
    public void printEmployees() {
        System.out.println("=======================");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
        System.out.println("=======================");
    }
}

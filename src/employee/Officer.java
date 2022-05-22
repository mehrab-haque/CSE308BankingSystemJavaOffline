package employee;

import config.Constants;

public class Officer extends Employee {
    public Officer(String name){
        super(name);
    }

    @Override
    public String getType() {
        return Constants.EMPLOYEE_TYPE_OFFICER;
    }
}

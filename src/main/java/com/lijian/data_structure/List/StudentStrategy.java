package com.lijian.data_structure.List;



public class StudentStrategy implements Strategy {
    @Override
    public boolean equal(Object o1, Object o2) {
        if (o1 instanceof Student && o2 instanceof Student) {
            Student student1 = (Student) o1;
            Student student2 = (Student) o2;
            return student1.getSId().equals(student2.getSId());

        }
        else{
            return  false;
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Student && o2 instanceof Student) {
            Student student1 = (Student) o1;
            Student student2 = (Student) o2;
            return student1.getSId().compareTo(student2.getSId());

        }
        else{
            return o1.toString().compareTo(o2.toString());

        }
    }
}

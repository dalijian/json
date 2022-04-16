package com.lijian.deserialize;

public class Employee  implements java.io.Serializable{


   public String name;
   public String address;
   public int SSN;
   public int number;

   public Employee() {
   }

   public Employee(String name, String address, int SSN, int number) {

      this.name = name;
      this.address = address;
      this.SSN = SSN;
      this.number = number;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public int getSSN() {
      return SSN;
   }

   public void setSSN(int SSN) {
      this.SSN = SSN;
   }

   public int getNumber() {
      return number;
   }

   public void setNumber(int number) {
      this.number = number;
   }
}
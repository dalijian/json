//package com.lijian.jvm;
//
//public interface Customer {
//    boolean isVIP();
//
//}
//class Merchant{
//    public Number actionPrice(double price, Customer customer) {
//    return  0;
//    }
//}
//class NaiveMerchant extends Merchant{
//    @Override
//    public Number actionPrice(double price, Customer customer) {
//        return super.actionPrice(price, customer);
//    }
//}
//class Merchant<T extends Customer>{
//    public double actionPrice(double price, T customer) {
//
//    }
//}
//class VIPOnlyMerchant extends Merchant<VIP>{
//    @Override
//    public Number actionPrice(double price, Customer customer) {
//        return super.actionPrice(price, customer);
//    }
//}

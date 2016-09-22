package com.javaweb.exception;

public class InsufficientBalanceException extends Exception {
   public InsufficientBalanceException(){
	   String msg="Sorry, insufficient money in your account.";
   }

}

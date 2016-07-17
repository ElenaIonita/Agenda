/*
 * 2016
 */
package com.agenda.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Elena Ionita
 */
public class Subscriber {
    private long id;
    private String firstName;
    private String lastName;
    private String cnp;
    private String mobileNumber;
    
    public Subscriber(long id, String firtName, String lastName, String cnp, String mobileNumber) throws IllegalArgumentException{
        
          if( firstName == null || firstName.length()==0){
	                throw new IllegalArgumentException("You must enter a first name!");
	  }
          
          if ( !firstName.matches("[A-Za-z\\-]+")){
            throw new IllegalArgumentException("You must enter a valid first name!");
          }
          
          if( lastName == null || lastName.length()==0){
	                throw new IllegalArgumentException("You must enter a last name!");
	  }
          
          if ( !lastName.matches("[A-Za-z\\-]+")){
                        throw new IllegalArgumentException("You must enter a valid last name!");
          }
          
          if ( cnp==null){
                        throw new IllegalArgumentException("You must enter a CNP!");
          }
          if((!cnp.matches("^[1-2]{1}+\\d{2}+[1/0]{1}+\\d{1}+[1-3]{1}+\\d+")) || (cnp.length()!= 13)){
                        throw new IllegalArgumentException("You must enter a valid CNP!");
          }
          if( mobileNumber == null){
	                throw new IllegalArgumentException("You must enter a phone number!");
	  }
          if (!mobileNumber.matches("^0\\d+") || mobileNumber.length()!=10) {
                        throw new IllegalArgumentException("You must enter a valid phone number!");
          }
                             
          this.id=id;
          this.firstName=firstName;
          this.lastName=lastName;
          this.cnp=cnp;
          this.mobileNumber=mobileNumber;
    }   
    
    public long getId(){
        return id;
            }
    
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        if( firstName == null || firstName.length()==0){
	                throw new IllegalArgumentException("You must enter a first name!");
	  }
          
        if ( !firstName.matches("[A-Za-z\\-]+")){
            throw new IllegalArgumentException("You must enter a valid first name!");
          }
    this.firstName=firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    public void setLastName (String lastName) {
        if( lastName == null || lastName.length()==0){
	                throw new IllegalArgumentException("You must enter a last name!");
	  }
          
        if ( !lastName.matches("[A-Za-z\\-]+")){
                        throw new IllegalArgumentException("You must enter a valid last name!");
          }
    this.lastName=lastName;
    }
    
    public String getCnp(){
        return cnp;
    }
    public void setCnp (String cnp ){
        if ( cnp==null){
                        throw new IllegalArgumentException("You must enter a CNP!");
          }
        if((!cnp.matches("^[1-2]{1}+\\d{2}+[1/0]{1}+\\d{1}+[1-3]{1}+\\d+")) || (cnp.length()!= 13)){
                        throw new IllegalArgumentException("You must enter a valid CNP!");
        }
    this.cnp=cnp;
    }
    
    public String getMobileNumber(){
        return mobileNumber;
    }
    public void setMobileNumber (String mobileNumber ) {
        if( mobileNumber == null){
	                throw new IllegalArgumentException("You must enter a phone number!");
	  }
        if (!mobileNumber.matches("^0\\d+") || mobileNumber.length()!=10) {
                        throw new IllegalArgumentException("You must enter a valid phone number!");
        }
    this.mobileNumber=mobileNumber;
    }
    
@Override

public String toString(){
    return "First name:"+firstName+"Last name:"+lastName+"CNP"+cnp+"Mobile No."+mobileNumber;
}

          
        
    }
           
    

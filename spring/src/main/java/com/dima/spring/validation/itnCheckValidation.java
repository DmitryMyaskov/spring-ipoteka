package com.dima.spring.validation;

import com.dima.spring.entity.Seller;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class itnCheckValidation implements ConstraintValidator<ItnCheck, Seller> {


    @Override
    public boolean isValid(Seller value, ConstraintValidatorContext constraintValidatorContext) {

        if(value.getFaceItem().toString()=="PhisFace"){
            value.setITN(0L);
            return true;
        }else if(value.getFaceItem().toString()=="IndivFace" &&
                (String.valueOf(value.getITN()).length()==10 || String.valueOf(value.getITN()).length()==12)){
            return inn(String.valueOf(value.getITN()).split(""));
        }

        return false;
    }

    public boolean inn(String [] itn){

        int sum=0;
        int control=0;

        int [] inn10={2,4,10,3,5,9,4,6,8};

        int [] inn12={7,2,4,10,3,5,9,4,6,8};

        int [] inn12V={3,7,2,4,10,3,5,9,4,6,8};
        int [] arr= new int[itn.length];
        for(int i=0;i<itn.length;i++){
            arr[i]= Integer.parseInt(itn[i]);
        }

        if(arr.length == 10){
            sum=circle(arr,inn10,9);
            control=sum-(sum/11)*11;
            return control == arr[9] || control == 10? true : false;
        }else if(arr.length==12){
            sum=circle(arr,inn12,10);
            control=sum-((sum/11)*11);

            if(control == arr[10] || control == 10){
                sum=circle(arr,inn12V,11);
                control=sum-((sum/11)*11);
                return control==arr[11] || control == 10 ? true :false;
            }else
                return false;
        }
        return false;
    }

    public int circle(int [] arr,int[] inn, int len){
        int sum=0;
        for(int i=0;i<len;i++){
            sum+=arr[i]*inn[i];
        }
        return sum;
    }
}
package com.example.login_test3;

public class dietInfo {

    public String name;
    public String type;
    public String standard;
    public String calories;
   // public String email;

    public dietInfo(){

    }

    public dietInfo(String name, String type, String calories, String standard){
        this.name = name;
        this.type = type;
        this.calories =calories;
        this.standard = standard;
      //  this.email= email;

    }
    public String getName()
    {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return type;
    }
    public void SetType(String type){
        this.type = type;
    }

    public String getStandard(){
        return standard;
    }

    public void SetStandard(String standard){
        this.standard= standard;
    }

    public String getCalories(){
        return calories;
    }

   // public String getEmail(){
    //    return email;
   // }

    public void SetCalories(String calories){
        this.calories=calories;
    }
}

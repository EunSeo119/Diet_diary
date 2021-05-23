package com.example.login_test3;

import com.google.firebase.auth.FirebaseUser;

/**
 * 사용자 계정 정보 모델 클래스
 */
public class DietAccess
{
    private String idToken;     //Firebase Uid (고유 토큰정보)
    private String calories;
    private String dname;
    private String standard;
    private String type;

    public DietAccess(){}

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

}

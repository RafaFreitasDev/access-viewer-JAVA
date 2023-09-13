package br.com.rafael.accessviewerjava.model;

public enum UserType {
    //os enums são separados por virgula
    ADMIN("ADMIN"),
    SELLER("SELLER"),
    COMMON("COMMON");

    //atributo
    private String type;

    //constructor
    UserType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

package TCP;

import java.io.*;

public class Address implements Serializable {
    private static final long serialVersionUID = 20180801L;
    private int id; 
    private String code;
    private String addressLine;
    private String city;
    private String postalCode; 
    
    public Address(int id, String code, String addressLine, String city, String postalCode){
        this.id=id;
        this.code=code;
        this.addressLine=addressLine;
        this.city=city;
        this.postalCode=postalCode;
    }

    //getter
    public int getId(){
        return id;
    }
    public String getCode(){
        return code;
    }
    public String getAddressLine(){
        return addressLine;
    }
    public String getCity(){
        return city;
    }
    public String getPosCode(){
        return postalCode;
    }
    //setter
    public void setId(int id){this.id=id;}
    public void setCode(String code){this.code=code;}
    public void setAddress(String addressLine){this.addressLine=addressLine;}
    public void setCity(String city){this.city=city;}
    public void setposCode(String postalCode){this.postalCode=postalCode;}
}

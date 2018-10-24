package model;

public class Pokemon {
    String name;
    private String url;

  public Pokemon() {}
  
  public String getName(){
    return name;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public String getUrl(){
    return url;
  }
  
  public void setUrl(String url){
    this.url = url;
  }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import tools.SymmetricCrypt;

/**
 *
 * @author Melnikov
 */
@Entity
public class AccountBox implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String urlLogin;
    private String urlPassword;
    private String url;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Picture picture;
    @Transient
    private final SymmetricCrypt sc;

    public AccountBox() {
        sc = new SymmetricCrypt();
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlLogin() {
        return urlLogin;
    }

    public void setUrlLogin(String urlLogin) {
        this.urlLogin = urlLogin;
    }

    public String getUrlPassword() {
        return sc.decrypt(urlPassword);
    }

    public void setUrlPassword(String urlPassword) {
        this.urlPassword = sc.encrypt(urlPassword);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = this.addProtocolToUrl(url);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.urlLogin);
        hash = 41 * hash + Objects.hashCode(this.urlPassword);
        hash = 41 * hash + Objects.hashCode(this.url);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountBox other = (AccountBox) obj;
        if (!Objects.equals(this.urlLogin, other.urlLogin)) {
            return false;
        }
        if (!Objects.equals(this.urlPassword, other.urlPassword)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "AccountBox{" 
                + "id=" + id 
                + ", name=" + name 
                + ", urlLogin=" + urlLogin 
                + ", url=" + url 
                + ", picture=" + picture.getDescription()
                + '}';
    }

   private String addProtocolToUrl(String url){
       String pathern1 = "http://";
       String pathern2 = "https://";
       String localhost = "localhost";
       String subStr1 = url.substring(0, 7);
       String subStr2 = url.substring(0, 8);
       String subStr3 = url.substring(0, 9);
       
       if(subStr1.equals(pathern1) || subStr2.equals(pathern2)){
           return url;
       }else{
           if(subStr3.equals(localhost)){
               url = pathern1+url;
               return url;
           }
           url = pathern2+url;
           return url;
       }
   }
}

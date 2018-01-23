
package com.example.sapir.shapeit;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * this class is used to link the functionality of the application to db.
 * this class contains functions that perform various functions in the application and update various tables in db.
 * it is important to note that the functions in this class are written in php and are in direct contact with the application through this class.
 */
public class PHP {
    /**
     * attributes
     */
    String url = "http://shapeitsystem.com/mobile.php?";
    Scanner scan;
    URL u;

    /**
     * @param user
     * @param password
     * @return str.
     * @throws Exception
     */
    public String login(String user, String password) throws Exception {
        String url = this.url + "func=login&user=" + user + "&password=" + password;

        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }

    /**
     * @param email
     * @return str.
     * @throws Exception
     */

    public String ResetPassword(String email) throws Exception {
        String url = this.url + "func=resetpassword&email=" + email;
        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }

    /**
     * @param id
     * @return str.
     * @throws Exception
     */

    public String getcustomerdetails(String id) throws Exception {
        String url = this.url + "func=getcustomerdetails&id=" + id;
        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }

    /**
     * @param day
     * @return str.
     * @throws Exception
     */
    public String getlesson(String day) throws Exception {
        String url = this.url + "func=getlesson&day=" + day;
        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }

    /**
     * @param id
     * @param email
     * @param password
     * @param height
     * @param weight
     * @return str.
     * @throws Exception
     */
    public String updatecustomers(String id, String email, String password, String height, String weight) throws Exception {
        String url = this.url + "func=updatecustomers&id=" + id + "&email=" + email + "&password=" + password + "&height=" + height + "&weight=" + weight;
        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }

    /**
     * @param id
     * @return str.
     * @throws Exception
     */
    public String getnotificationbyid(String id) throws Exception {
        String url = this.url + "func=getnotificationbyid&id=" + id;
        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }

    /**
     * @param idcustomer
     * @param scheduleid
     * @return str.
     * @throws Exception
     */
    public String inserthistoryrecords(String idcustomer, String scheduleid) throws Exception {
        String url = this.url + "func=inserthistoryrecords&idcustomer=" + idcustomer + "$scheduleid=" + scheduleid;
        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }

    /**
     * @param id
     * @return str.
     * @throws Exception
     */
    public String updatecardbalance(String id) throws Exception {
        String url = this.url + "func=updatecardbalance&id=" + id;
        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }

    /**
     * @param idcustomer
     * @param scheduleid
     * @return str.
     * @throws Exception
     */
    public String updatecancellesson(String idcustomer, String scheduleid) throws Exception {
        String url = this.url + "func=updatecancellesson&idcustomer=" + idcustomer + "$scheduleid=" + scheduleid;
        u = new URL(url);
        scan = new Scanner(u.openStream());
        String str = scan.nextLine();
        return str;
    }
}


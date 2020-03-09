package controller;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletContext;



import model.BEANS.Property;
import model.BEANS.Seller;
import model.DAO.ItemDAO;
import model.DAO.UserDAO;

public class Tester {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
 
		
		int[] nums = {1,2,3,4,5,6,7,8,9,10};
		
		
        System.out.println(nums.length/3);
		
		
		
		/*
		java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());

        int futureDay = 0;
        int pastDay = 233;

        java.sql.Date futureDate = addDays(todaysDate, futureDay);
        java.sql.Date pastDate = subtractDays(todaysDate, pastDay);

        //System.out.println("futureDate =>>> " + futureDate);
        //System.out.println("pastDate =>>> " + pastDate);
        System.out.println(futureDate.before(todaysDate));
*/

	}
	/*
	public static java.sql.Date addDays(java.sql.Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static java.sql.Date subtractDays(java.sql.Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -days);
        return new java.sql.Date(c.getTimeInMillis());
    }
    */
	

}

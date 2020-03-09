package quartz;

import java.util.ArrayList;

import model.DAO.ItemDAO;

/** THIS JOB IS CALLED UPON DAILY AT 00:00
 * 
 * @author rpa495
 *
 */
public class ExpireJob //implements //Job

{
	
	/*
	@Override
	public void execute(Object o) throws Exception {
		
		ItemDAO id;
		
		// Todays date:
		long time = System.currentTimeMillis();
		java.sql.Date todaysDate = new java.sql.Date(time);
		
		id = new ItemDAO();
		
		// Get ids of expiring propeties:
		ArrayList<Integer> expiringPropertyIds = id.getExpiringPropertyIds(todaysDate);
		
		if(expiringPropertyIds.size()!=0) {
			
			// Use ItemDAO to update their status to expired:
			for(int propertyId:expiringPropertyIds) {
				
				id = new ItemDAO();
				
				id.updateStatus(propertyId, "expired");
				
			}
			
		}

	}
	*/

}

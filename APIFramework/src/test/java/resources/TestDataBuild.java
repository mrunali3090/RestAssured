package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		AddPlace p=new	AddPlace(); // create obj of the setter method
		
		//data is now handled from cucumber data driven mechanism
		
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setName(name);
		p.setWebsite("http://google.com");
		
		//create list for Types and send the list here
		
		List<String>mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		p.setTypes(mylist);
		
		//Setlocation is expecting an obj of location
		
		location l=new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		
		return p;
	}

	public String deletePlacePayload(String placeId)
	{
		return "{\n" + 
				"    \"place_id\":\""+placeId+"\"        \n" + 
				"}";
	}
	

}

package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language,String address) {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("+91 8765456784");
		List<String> myList = new ArrayList<String>();
		myList.add("ShoePark");
		myList.add("Shop");
		p.setTypes(myList);
		p.setWebsite("https://abc.com");

		Location l = new Location();
		l.setLat(-38.43567);
		l.setLng(33.435678);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String place_Id) {
		return "{\"place_id\":\""+place_Id+"\"}";
	}
}

package frmwrk.resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

		public AddPlace addPlacePayLoad(String name, String language, String address)
		{
			
			 AddPlace addplace = new AddPlace();
			addplace.setAccuracy(50);
			addplace.setAddress(address);
			addplace.setLanguage(language);
			addplace.setPhone_number("(+91) 983 893 3937");
			addplace.setName(name);
			addplace.setWebsite("http://google.com");
			List<String> listTypes=new ArrayList<String>();
			listTypes.add("shoe park");
			listTypes.add("shop");
			addplace.setTypes(listTypes);
			
			Location l=new Location();
			l.setLat(-38.383494);
			l.setLng(33.427362);			
			addplace.setLocation(l);
			return addplace;
		}
		
		public String deletePlacePayLoad(String place_id) {
			return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
		}
}

package net.viralpatel.spring.autocomplete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class DummyDB {
    private List<String> countries;
    private List<String> tags;
    
    public DummyDB() {

    	String data = "Afghanistan,	Albania, Algeria, Andorra, Angola, Antigua & Deps,"+ 
    			"Argentina,	Armenia, Australia,	Austria,Azerbaijan,Bahamas,Bahrain,Bangladesh,Barbados,"+ 
    			"Belarus,Belgium,Belize,Benin,Bhutan,Bolivia,Bosnia Herzegovina,Botswana,Brazil,Brunei,"+ 
    			"Bulgaria,Burkina,Burundi,Cambodia,Cameroon,Canada,Cape Verde,Central African Rep,Chad,"+ 
    			"Chile,China,Colombia,Comoros,Congo,Congo {Democratic Rep},Costa Rica,Croatia,Cuba,Cyprus,"+ 
    			"Czech Republic,Denmark,Djibouti,Dominica,Dominican Republic,East Timor,Ecuador,Egypt,El Salvador,"+ 
    			"Equatorial Guinea,	Eritrea,Estonia,Ethiopia,Fiji,Finland,France,Gabon,Gambia,Georgia,Germany,"+ 
    			"Ghana,	Greece,	Grenada,Guatemala,Guinea,Guinea-Bissau,Guyana,Haiti,Honduras,Hungary,Iceland,"+ 
    			"India,	Indonesia,Iran,Iraq,Ireland {Republic},Israel,Italy,Ivory Coast,Jamaica,Japan,"+ 
    			"Jordan,Kazakhstan,Kenya,Kiribati,Korea North,Korea South,Kosovo,Kuwait,Kyrgyzstan,Laos,"+ 
    			"Latvia,Lebanon,Lesotho,Liberia,Libya,Liechtenstein,Lithuania,Luxembourg,Macedonia,Madagascar,"+ 
    			"Malawi,Malaysia,Maldives,Mali,Malta,Marshall Islands,Mauritania,Mauritius,Mexico,Micronesia,"+ 
    			"Moldova,Monaco,Mongolia,Montenegro,Morocco,Mozambique,Myanmar {Burma},Namibia,Nauru,Nepal,"+ 
    			"Netherlands,New Zealand,Nicaragua,Niger,Nigeria,Norway,Oman,Pakistan,Palau,Panama,Papua New Guinea,"+ 
    			"Paraguay,Peru,Philippines,Poland,Portugal,Qatar,Romania,Russian Federation,Rwanda,St Kitts & Nevis,"+
    			"St Lucia,Saint Vincent & the Grenadines,Samoa,San Marino,Sao Tome & Principe,Saudi Arabia,Senegal,"+ 
    			"Serbia,Seychelles,Sierra Leone,Singapore,Slovakia,Slovenia,Solomon Islands,Somalia,South Africa,"+ 
    			"Spain,Sri Lanka,Sudan,Suriname,Swaziland,Sweden,Switzerland,Syria,Taiwan,Tajikistan,Tanzania, "+
    			"Thailand,Togo,Tonga,Trinidad & Tobago,Tunisia,Turkey,Turkmenistan,Tuvalu,Uganda,Ukraine,United Arab Emirates,"+
    			"United Kingdom,United States,Uruguay,Uzbekistan,Vanuatu,Vatican City,Venezuela,Vietnam,Yemen,Zambia,Zimbabwe";

    	countries = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(data, ",");
        
        //Parse the country CSV list and set as Array
        while(st.hasMoreTokens()) {
            countries.add(st.nextToken().trim());
        }

        String strTags = "SharePoint,Spring Java,Spring,Struts,Java,JQuery,ASP,PHP,JavaScript,MySQL,ASP,.NET";
        tags = new ArrayList<String>();
        StringTokenizer st2 = new StringTokenizer(strTags, ",");
        
        //Parse the tags CSV list and set as Array
        while(st2.hasMoreTokens()) {
            tags.add(st2.nextToken().trim());
        }
    
    }
 
    public List<String> getCountryList(String query) {
            	
    	String country = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i < countries.size(); i++) {
            country = countries.get(i).toLowerCase();
            if(country.startsWith(query)) {
                matched.add(countries.get(i));
            }
        }
        return matched;
    }

    public HashSet<String> getTechList(String query,String allQuery) {
        String country = null;
        query=query.trim();
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i < tags.size(); i++) {
            country = tags.get(i).toLowerCase();
            if(country.startsWith(query)) {
            	
            	if(allQuery != null && !allQuery.isEmpty() && allQuery.contains(",")){
            	//split allQuery
            	System.out.println("allQuery = "+ allQuery);
            	String allQueryArr[]=allQuery.split(",");
            	if(allQueryArr.length > 1){
            		
	            	for (int j = 0; j < allQueryArr.length-1; j++) {
	            		if(!(tags.get(i).trim().equalsIgnoreCase(allQueryArr[j].trim()))){
	            			System.out.println("a1) data= "+ tags.get(i) + "i= "+ i + "j= "+j);
	            			
	            			matched.add(tags.get(i).trim());
	            			
	            		} 
					}
            	}
            	
            	}else{
            		System.out.println("a2");
            		 matched.add(tags.get(i));
            	}
            }
        }
        
        HashSet<String> setList=new HashSet<String>(matched);
        
        if(allQuery != null && !allQuery.isEmpty() && allQuery.contains(",")){
        	//split allQuery
        	String allQueryArr[]=allQuery.split(",");
        	System.out.println("allQuery = "+ allQuery);
        	
        		for (int j = 0; j < allQueryArr.length; j++) {
        			setList.remove(allQueryArr[j].trim());
				}
        		System.out.println("after delete = "+matched);
        	}		
        
        System.out.println(setList);
        return setList;
}
}
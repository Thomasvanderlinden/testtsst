package nl.hu.bep;

import com.azure.storage.common.sas.AccountSasResourceType;
import nl.hu.bep.countrycase.model.Country;
import nl.hu.bep.countrycase.model.World;
import nl.hu.bep.countrycase.webservices.WorldResource;

import javax.json.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.CheckedOutputStream;

public class TempMain {

    public static void createCustomer(String jsonBody){
        JsonObjectBuilder responseObejct = Json.createObjectBuilder();

        try{
            StringReader stringReader = new StringReader(jsonBody);
            JsonReader jsonReader = Json.createReader(stringReader);
            JsonObject jsonObject = jsonReader.readObject();

            String name = jsonObject.getString("name");
            String code = jsonObject.getString("code");


            Country c = new Country("hallotest", "hallo2", "testland", "hallo2", "hallo2", "hallo2",1.2, 1, "hallo2",2.1,1.2);
            World.getWorld().addCountry(c);
            responseObejct.add("message1", "iets gedaan");

        }catch (Exception e){
            responseObejct.add("message2", "oke");
        }
        System.out.println(responseObejct.build().toString());
    }
    public static void main(String[] args) {

                JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("code", "DE");
        ob.add("iso3", "DEU");
        ob.add("name", "Deuthsland");
        ob.add("capital", "DasE");
        ob.add("continent", "DasE");
        ob.add("region", "DdE");
        ob.add("surface", "DfgE");
        ob.add("population", "DhE");
        ob.add("government", "kkDE");
        ob.add("latitude", "D;;E");
        ob.add("longitude", "DE76t");
        ob.build();
        System.out.println(World.getWorld().getAllCountries().size());
        createCustomer(ob.toString());
        System.out.println(World.getWorld().getAllCountries().size());


    }
}




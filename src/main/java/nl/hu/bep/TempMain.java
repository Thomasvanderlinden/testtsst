package nl.hu.bep;

import com.azure.storage.common.sas.AccountSasResourceType;
import nl.hu.bep.countrycase.model.Country;
import nl.hu.bep.countrycase.model.World;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TempMain {

    public static void main(String[] args) {

        for (Country c : World.getWorld().get10LargestPopulations()) {
            JsonObjectBuilder ob = Json.createObjectBuilder();

            ob.add("name", c.getName());
            ob.add("population", c.getPopulation());
            System.out.println(ob.build().toString());
        }
    }
}




package nl.hu.bep.countrycase.webservices;


import com.azure.storage.common.sas.AccountSasResourceType;
import nl.hu.bep.countrycase.model.Country;
import nl.hu.bep.countrycase.model.World;
import org.glassfish.jersey.message.internal.CommittingOutputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.*;
import java.io.StringReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

@Path("/countries")
public class WorldResource {
    @POST
    @Produces("Application/json")
    public String voegEenLandToe(String JsonBody) {
        JsonObjectBuilder responseObject = Json.createObjectBuilder();


        try {
            StringReader strReader = new StringReader(JsonBody);
            JsonReader jsonReader = Json.createReader(strReader);
            JsonObject jsonObject = jsonReader.readObject();

            if (World.getWorld().getAllCountries().contains(World.getWorld().getCountryByCode(jsonObject.getString("code")))) {
                return "dit land bestaat al";
            } else {
                String code = jsonObject.getString("code");
                String iso3 = jsonObject.getString("iso3");
                String name = jsonObject.getString("name");
                String continent = jsonObject.getString("continent");
                String capital = jsonObject.getString("capital");
                String region = jsonObject.getString("region");
                int surface = Integer.parseInt(jsonObject.getString("surface"));
                int population = Integer.parseInt(jsonObject.getString("population"));
                String goverment = jsonObject.getString("government");
                double lat = Double.parseDouble(jsonObject.getString("latitude"));
                double lng = Double.parseDouble(jsonObject.getString("longitude"));

                Country c = new Country(code, iso3, name, continent, capital, region, surface, population, goverment, lat, lng);

                World.getWorld().addCountry(c);

                responseObject.add("message1", "land aangemaakt");
            }
            } catch(Exception e){
                responseObject.add("message2", "error" + e.getMessage());

            }
            System.out.println(World.getWorld().getAllCountries().toString());
            System.out.println(World.getWorld().getAllCountries().size());
            return responseObject.build().toString();

    }


    List<JsonObject> alleLandenLijstVanMij = new ArrayList<>();

    @GET
    public String lijstVanAlleLandenInformatie() {
        for (Country c : World.getWorld().getAllCountries()) {
            JsonObjectBuilder ob = Json.createObjectBuilder();
            ob.add("code", c.getCode());
            ob.add("iso3", c.getIso3());
            ob.add("name", c.getName());
            ob.add("continent", c.getContinent());
            ob.add("capital", c.getCapital());
            ob.add("region", c.getRegion());
            ob.add("surface", c.getSurface());
            ob.add("population", c.getPopulation());
            ob.add("goverment", c.getGovernment());
            ob.add("lat", c.getLatitude());
            ob.add("lng", c.getLatitude());
            alleLandenLijstVanMij.add(ob.build());
        }
        return alleLandenLijstVanMij.toString();
    }

    @GET
    @Path("/{code}")
    @Produces("Application/json")
    public String getLandinformatie(@PathParam("code") String code) {
        Country land = World.getWorld().getCountryByCode(code.toUpperCase());

        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("code", land.getCode());
        ob.add("iso3", land.getIso3());
        ob.add("name", land.getName());
        ob.add("continent", land.getContinent());
        ob.add("capital", land.getCapital());
        ob.add("region", land.getRegion());
        ob.add("surface", land.getSurface());
        ob.add("population", land.getPopulation());
        ob.add("goverment", land.getGovernment());
        ob.add("lat", land.getLatitude());
        ob.add("lng", land.getLatitude());
        return ob.build().toString();
    }

    @GET
    @Path("/largestsurfaces")
    @Produces("Application/json")
    public String vraagOppervlakte() {

        List<JsonObject> oppervlakteLijstLanden = new ArrayList<>();
        for (Country c1 : World.getWorld().get10LargestSurfaces()) {
            JsonObjectBuilder ob1 = Json.createObjectBuilder();
            ob1.add("naam", c1.getName());
            ob1.add("surface", c1.getSurface());
            oppervlakteLijstLanden.add(ob1.build());

        }
        return oppervlakteLijstLanden.toString();
    }

    @GET
    @Path("/largestpopulations")
    @Produces("Application/json")
    public String grootstePopulatie() {
        List<JsonObject> grootstePopulatieLanden = new ArrayList<>();
        for (Country c2 : World.getWorld().get10LargestPopulations()) {
            JsonObjectBuilder ob2 = Json.createObjectBuilder();
            ob2.add("naam", c2.getName());
            ob2.add("population", c2.getPopulation());
            grootstePopulatieLanden.add(ob2.build());
        }
        return grootstePopulatieLanden.toString();
    }
}

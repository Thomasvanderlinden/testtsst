package nl.hu.bep.countrycase.webservices;


import com.azure.storage.common.sas.AccountSasResourceType;
import nl.hu.bep.countrycase.model.Country;
import nl.hu.bep.countrycase.model.World;
import org.glassfish.jersey.message.internal.CommittingOutputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

@Path("/countries")
public class WorldResource   {
    List <JsonObject> alleLandenLijstVanMij = new ArrayList<>();

    @GET
    public String lijstVanAlleLandenInformatie() {


        for(Country c : World.getWorld().getAllCountries()) {
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
        }return alleLandenLijstVanMij.toString();
    }

    @GET
    @Path("/{code}")
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
    public String vraagOppervlakte() {

        List<JsonObject> oppervlakteLijstLanden = new ArrayList<>();
        for(Country c1 : World.getWorld().get10LargestSurfaces()) {
            JsonObjectBuilder ob1 = Json.createObjectBuilder();
            ob1.add("naam", c1.getName());
            ob1.add("surface", c1.getSurface());
            oppervlakteLijstLanden.add(ob1.build());

        }return oppervlakteLijstLanden.toString();
    }

    @GET
    @Path("/largestpopulations")
    public String grootstePopulatie(){
        List<JsonObject> grootstePopulatieLanden = new ArrayList<>();
        for(Country c2 : World.getWorld().get10LargestPopulations()){
            JsonObjectBuilder ob2 = Json.createObjectBuilder();
            ob2.add("naam", c2.getName());
            ob2.add("population", c2.getPopulation());
            grootstePopulatieLanden.add(ob2.build());
        }return grootstePopulatieLanden.toString();
    }
}

package nl.hu.bep.countrycase.persistence;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.hu.bep.countrycase.model.Country;
import nl.hu.bep.countrycase.model.World;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.*;
import java.util.zip.CheckedOutputStream;

public class PersistentieManager {
    private final static String ENDPOINT = "https://doeiets.blob.core.windows.net";
    private final static String SASTOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2022-05-30T17:30:46Z&st=2022-05-19T09:30:46Z&spr=https&sig=FWGib0qcn73eAkK1nWQsCUDF0%2BS2hMSVXri9TWfo1fg%3D";
    private final static String CONTAINER = "doeiets";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKEN).containerName(CONTAINER).buildClient();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("hallo", "thomas");
        ob.add("doei", "frank");
        ob.build().toString();
        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("wereldContainer");
            if (blob.exists()) {
                blob.upload(BinaryData.fromObject(ob));

            }
        }
    }

}
//    public static void saveWorldFromAzure() throws IOException {
//        if (!blobContainer.exists()) {
//            blobContainer.create();
//        }
//        BlobClient blob = blobContainer.getBlobClient("wereldContainer");
//        World wereld = World.getWorld();
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(wereld);
//
//        byte[] bytez = baos.toByteArray();
//
//        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
//        blob.upload(bais, bytez.length, true);
//
//        oos.close();
//        baos.close();
//        bais.close();
//
//    }
//}

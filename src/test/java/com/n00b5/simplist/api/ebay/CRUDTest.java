package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.n00b5.simplist.api.ebay.enums.*;
import com.n00b5.simplist.api.ebay.inventory.*;
import com.n00b5.simplist.api.ebay.location.Address;
import com.n00b5.simplist.api.ebay.location.InventoryLocation;
import com.n00b5.simplist.api.ebay.location.InventoryLocations;
import com.n00b5.simplist.api.ebay.location.Location;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/5/17
 */
public class CRUDTest {
    String token;
    @Before
    public void setup(){
        token = "v^1.1#i^1#r^0#p^3#I^3#f^0#t^H4sIAAAAAAAAAOVXa2wUVRTudttCgzw0BEEF1wEJQmb2zmNnd4d2dftASugDtuVlTHN35k47dnZmnTvTdsHKWrGJShpCIASIgCIaghEJiYSIpNFGf/iCyA+Ipj6jBAOJgsEfjXhn+2BbFfogsYn7Z3PvPa/vnO+cuRekCwoXdyzvuD7VMyn3QBqkcz0edgooLMhfMs2be19+DsgS8BxIL0jntXsvFmGY0JPSaoSTpoGRrzWhG1jKbBZTjmVIJsQalgyYQFiyZSkWrVwpcQyQkpZpm7KpU76KsmJKDSphLiSyrCAHBU6EZNcYsFlruuecHAqykI3zMMCHATnH2EEVBrahYRdTHGCDNGBpEKwFIYkTJV5geBDYQPnWIAtrpkFEGEBFMuFKGV0rK9ZbhwoxRpZNjFCRiuiyWHW0oqy8qrbIn2Ur0p+HmA1tBw9dlZoK8q2BuoNu7QZnpKWYI8sIY8of6fMw1KgUHQhmDOFnUs3zobCMhFCIUwNhhRfuSCqXmVYC2reOw93RFFrNiErIsDU7dbuMkmzEn0Ky3b+qIiYqynzu3yoH6pqqIauYKi+Jrq+Lla+mfLGaGsts1hSkuEhZnue5EB8IUBEbYZJCZNVjZDcS1umagfvd9dnsT/Ywf6WmoWhu6rCvyrRLEIkdDc8Qm5UhIlRtVFtR1XbjypJjQX8muVBwg1vavlo6dqPhVhclSDp8meXt6zBAjJtUuFPUEONCGPB8EKpIBEhWsqjh9vqY6RFxKxStqfGjOEzRCWg1ITupQxnRMkmtk0CWpkiCEBdEKKs0qZlMC0pApsPhAKI5JCKRg7wAVOH/xxDbtrS4Y6NBlgw/yEAtpmKymUQ1pq7JKWq4SGb29HOiFRdTjbadlPz+lpYWpoVnTKvBzwHA+tdVrozJjShBhu+ArHZ7YVrLEFZGRAtrkp1KkmhaCfmIc6OBivCWUgMtO1XipMg6htzENgwQeEiEkeG7/wIVu1AnFkhXHxMDMKkxLscZ2Uz4TUj62d2qz0TsG4mQP+6kiH8FWYyFoGIaemrkeg0O4XCf9siUMKkG09eOBMagR7fXx2JgFDqa0Uy4bFqpUcIcqjwKHSjLpmPYY3HXrzoKDdXRVU3X3XYdi8Ms9dGEaUA9ZWsyHnQ5ri6LJpMVysTqskoyRzTDrKJjWiKpa9imYyXraCgEVFEQBJkOxBUhJANuXLgV1KzJqF6bYNgNR9fHhauy4R8hkV4/+B/CqvJHx4WqDDVPNJaygsKKohCnYYCL0wIMBAlFQZgOQXLhEUNBMRyE48JcqmtkMtSmJtpHcLmJbaSMDxq5jU4sUO6EGRgwUOQArYSDpKpCCNAhlZdpMm/iI4U8bCPrSve3+7x/6LM6kpP5se2e90G75yR5mYMgoNkl4JECb12e9y4KazZiMDSUuNnKaFBlsNZgkFejhZgmlEpCzcot8FT+sHX95qwH/YEnwezBJ32hl52S9b4HD9w8yWen3zuVDQKWOA1xIi9sAPNvnuaxs/JmJmf80tvx4RetFV27js5++8gL7cy7Dpg6KOTx5OfktXty5rKHOkur5q+LFHROr/9446vdu3amz12d82bXr6vXXvq27XjJzoMzvzROrl31KH1MffHGwzu53p+6C79KdT57o+tSx5XJ99TZ5Q2/z+o9//1H215++r1FJ0q8rzddrU++g7p3v8I1bddeu3Z9+pWL87cd6j671EMdnnHkfNGmLVe7P2dn7Vn0NZ/e33P0dNGiM9uemxt7/vEXul6Kzqlb+Ic+d1LnNPDgip5zzuLLPUurVfPUn4fz9m05zTyzdstC7xsL5qXn7a3e3XZ322P1PdMuFD3x855dx1bs3mRcDmzt+WzHb9cucj9eKP9k8sq28Objh7/Z0UN/8Fbh3vvb95Ua0f3fneo982nl2WXbNxaeeOhEXxn/ApTRsHxqEQAA";
    }
    @Test
    public void createOrReplaceInventoryItem() throws IOException {
        InventoryItem inventoryItem =
                new InventoryItem();
        Product product =
                new Product();
        PackageWeightAndSize packageWeightAndSize=
                new PackageWeightAndSize();
        Availability availability =
                new Availability();
        ShipToLocationAvailability shipToLocationAvailability =
                new ShipToLocationAvailability();

        product.setTitle("Test listing - do not bid or buy-Rolex watch");
        Map<String,String[]> aspects = new HashMap<String, String[]>();
        String brand[] = new String[1];
        brand[0] = "Rolex";
        String material []= new String[1];
        material[0] = "18K gold";

        aspects.put("Brand",brand);
        aspects.put("Material",material);
        String[] imageUrls = new String[2];
        imageUrls[0]="http://kasjdfkda";
        imageUrls[1]="http://kasjdfkda1234";

        product.setAspects(aspects);
        product.setDescription("Some description akjsdfkajsndfkjansdkfjnaskdjfnakjsdnfkajsndfkjansdkfjnaksdjnf");
        product.setImageUrls(imageUrls);

        Dimension dimension = new Dimension();
        dimension.setHeight(15);
        dimension.setLength(25);
        dimension.setWidth(10);
        dimension.setUnit(LengthUnitOfMeasureEnum.INCH.toString());

        Weight weight = new Weight();
        weight.setUnit(WeightUnitOfMeasureEnum.OUNCE.toString());
        weight.setValue(15);

        packageWeightAndSize.setDimension(dimension);
        packageWeightAndSize.setPackageType(PackageTypeEnum.MAILING_BOX.toString());
        packageWeightAndSize.setWeight(weight);

        shipToLocationAvailability.setQuantity(53);
        availability.setShipToLocationAvailability(shipToLocationAvailability);

        inventoryItem.setProduct(product);
        inventoryItem.setAvailability(availability);
        inventoryItem.setCondition(ConditionEnum.NEW_OTHER.toString());
        inventoryItem.setConditionDescription("Deskdjfksjdfklsjdflkadf");
        inventoryItem.setPackageWeightAndSize(packageWeightAndSize);
        inventoryItem.setSku("Rolex123");
        HttpResponse response = new eBayAPI().createOrReplaceInventoryItem(inventoryItem, token);
        System.out.println(response);
        assertEquals(204,new eBayAPI().createOrReplaceInventoryItem(inventoryItem, token).getStatusLine().getStatusCode());
    }
    @Test
    public void getInventoryItem() throws IOException {
        InventoryItem item = new eBayAPI().getInventoryItem("Rolex123",token);
        System.out.println(item);
        assertNotNull(item);
    }
    @Test
    public void getAllInventoryItems() throws IOException {
        InventoryItems items = new eBayAPI().getAllInventoryItems(token);
        System.out.println(items);
        assertNotNull(items);
    }
    @Test
    public void deleteInventoryItem() throws IOException {
        HttpResponse response = new eBayAPI().deleteInventoryItem("Rolex123",token);
        System.out.println(response);
        assertEquals(204,response.getStatusLine().getStatusCode());
    }

    @Test
    public void createInventoryLocation() throws IOException {
        InventoryLocation inventoryLocation = new InventoryLocation();
        Location location = new Location();

        Address address = new Address();
        address.setAddressLine1("2034 Broadway");
        address.setAddressLine2("Apt 5F");
        address.setCity("New York");
        address.setStateOrProvince("NY");
        address.setPostalCode("12342");
        address.setCountry(CountryCodeEnum.US.toString());

        location.setAddress(address);

        inventoryLocation.setLocationInstructions("Items ship from this address. Some other instructions!");
        inventoryLocation.setLocation(location);
        inventoryLocation.setName("ShopName");
        inventoryLocation.setMerchantLocationStatus(StatusEnum.ENABLED.toString());
        inventoryLocation.setLocationTypes(new String[]{StoreTypeEnum.STORE.toString()});
        inventoryLocation.setMerchantLocationKey("myFirstStore");
        HttpResponse response = new eBayAPI().createInventoryLocation(inventoryLocation, token);
        assertEquals(204, response.getStatusLine().getStatusCode());
    }

    @Test
    public void getInventoryLocation() throws IOException {
        InventoryLocation location = new eBayAPI().getInventoryLocation("myFirstStore", token);
        System.out.println(location);
        assertNotNull(location);
    }

    @Test
    public void getAllInventoryLocations() throws IOException {
        InventoryLocations locations = new eBayAPI().getAllInventoryLocations(token);
        System.out.println(locations);
        assertNotNull(locations);
    }

    @Test
    public void deleteInventoryLocation() throws IOException {
        HttpResponse response = new eBayAPI().deleteInventoryLocation("myFirstStore", token);
        System.out.println(response);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void updateInventoryLocation() throws IOException, JSONException {
        HttpResponse response = new eBayAPI().updateInventoryLocation("myFirstStore",
                "some location additional information",
                "some location instructions",
                "www.myfirststore.com",
                "myFirstStore",
                "234-244-1234", token);
        System.out.println(response);
        assertEquals(204, response.getStatusLine().getStatusCode());
    }

}

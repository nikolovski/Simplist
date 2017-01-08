package com.n00b5.simplist.api.ebay;

import com.n00b5.simplist.api.ebay.enums.*;
import com.n00b5.simplist.api.ebay.inventory.*;
import com.n00b5.simplist.api.ebay.location.Address;
import com.n00b5.simplist.api.ebay.location.InventoryLocation;
import com.n00b5.simplist.api.ebay.location.InventoryLocations;
import com.n00b5.simplist.api.ebay.location.Location;
import com.n00b5.simplist.api.ebay.offer.*;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
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
        token = "v^1.1#i^1#f^0#p^3#I^3#r^0#t^H4sIAAAAAAAAAOVXa2wURRzv9WUoUpBHFTTxXIoPYO9mb/deC714pa09SkvhCkKj1NnZ2evSvd1jZ7ftBYNNeZiAkqAxaTTEJgqIflHESEW+NIJATCRUosFnwCj6AWOUEA0kzl4fXKtCHyQ2sV+anfm/fr//7z83AzoKpyzcUb3j6jTXHbndHaAj1+XipoIphQWLivNy5xXkgCwDV3dHaUd+Z96lpQQmtZS4GpOUoRPsbk9qOhEzi2WMbeqiAYlKRB0mMREtJMajtStEnweIKdOwDGRojDtWUcbIQT9CUkiBPgyCCuLoqj4Ys8EoY5AEAYbIz/tkQZBkH90nxMYxnVhQt8oYH+CCLOBYEGoAPpHnRcB7/FygkXGvxSZRDZ2aeAATyZQrZnzNrFpvXiokBJsWDcJEYtGq+MporKKyrmGpNytWZICHuAUtmwz/WmbI2L0Waja+eRqSsRbjNkKYEMYb6c8wPKgYHSxmHOVnqA6E/YqfD8gwLAVCflm5LVRWGWYSWjevw1lRZVbJmIpYt1QrfStGKRvSRoysga86GiJW4Xb+rbKhpioqNsuYyvLo+jXxytWMO15fbxqtqoxlBynH87wvxPv9TMTChFKIzSaCrWaqOk3VyUC6/pgDZI/It8zQZdWhjrjrDKsc09rxSIZ8WQxRo5X6SjOqWE5d2XbCAJNCONjotLa/l7bVrDvdxUlKhzvzees+DArjhhRulzRwAHEK4qGAeBlISMiShjPr45ZHxOlQtL7eiyWYZpPQbMFWSoMIs4hSayexqcoinWshAJHC0p4hVpD9iA2H/Zj14QAO+CAvAEX4/ynEskxVsi08pJKRGxmoZUwcGSlcb2gqSjMjTTJnz4Am2kkZ02xZKdHrbWtr87TxHsNMeH0AcN51tSviqBknITNkq97amFUzgkWYehFVtNIpWk07FR9NrieYCG/K9dC00uV2mn7HsUNsYlDAwyqMjFz9F6jEgTq5QDr+hAaAKdXjaNyDjKTXgHSenaWmTMXu0Rh5JTtN88vY9JgYyoaupUfvl7Cphvu9R+dEaDc8/eNIYQxldGZ9PAHG4KPqrVTLhpkeI8zhzmPwgQgZtm6NJ92A6xg8FFtTVE1zxnU8CbPcx1KmDrW0pSIylHJCUxZNpWLy5JqyWnqOqLpRx8bVZEpTicXGy9exUPArAUEQEOuXZCGEgG9CuGXcqiLcpE4y7LqtaRPCVZv4R0h01l/7D2HVeaMTQlWBWyebSjlB5gIBQWKh3yexAvQHqURBmA1BeuEJhIKBcBBOCPMyTaUnQ0N6sv0IVhvEwvLEoNHb6OQC5ZwwgwcMDPgAK4eDtKtCCLAhhUcsPW+k0UIesZB1pfvbfd47/Fkdycn8cZ2uY6DT1UNf5iAIWG4ReKQwb01+3p0MUS3sIVCXJaPdo0LFQ9SETl+NJva04HQKqmZuoav24nPrn8l60Hc/Ce4ZetJPyeOmZr3vwX03dgq46XdP44KAAyHg43nAN4L5N3bzuZL82fqR2EH0/cOzDr30xUP7vgKFH9ZszAHThoxcroKc/E5XTt7imndqi6/2ulDv9uuzwltzZ8UemFN9V/GC65v6vutcW7mGnLny3rZNH8x/4uDuXaXXCj6pfOPa5sslR7ftutLH/9T14oYFy+cu7Al2tswF7/b8+tvx8LNKk3G6t48vKpz5yi9bNiAltuT5PRcei1f0nX6h9OVzHz26/8c3qy5+WV5z6sLeq5+dKHrcjPftbp1x+WzR+0X73pqb2DnzQPSEfP1MXDp84d5XS37/dHnxgzv+SNfNlI90/3w+Un2gsSqSk7s30bio8sjOb05ufWpO6ZLpR+/v2fz61zN2sebTp1YdU+Yluo6f78qtaTl8aU/65Me4+Nuu4xsPzz+0t2v2lv2fL377h96j20v+PDej5+yl/jb+Bezgzr5qEQAA";
    }
    @Test
    public void createOrReplaceInventoryItemTest() throws IOException {
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
        imageUrls[0] = "http://cdn2.jomashop.com/media/catalog/product/r/o/rolex-sea-dweller-4000-black-dial-stainless-steel-rolex-oyster-automatic-men_s-watch-116600bkso.jpg";
        imageUrls[1] = "https://sep.yimg.com/ay/yhst-92803816272180/rolex-114060-submariner-mens-automatic-watch-4.jpg";

        product.setAspects(aspects);
        product.setDescription("Although the Rolex GMT-Master was designed essentially for professional use, its combination of peerless functionality and rugged good looks has attracted a wider travelling public. As well as appreciating its ability to display different time zones, these travellers admire the robustness and versatile appearance that make the GMT-Master eminently suitable for globetrotting and, indeed, for any occasion.");
        product.setImageUrls(imageUrls);
        product.setBrand("Rolex");
        product.setMpn("MPN123");

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

        shipToLocationAvailability.setQuantity(10);
        availability.setShipToLocationAvailability(shipToLocationAvailability);

        inventoryItem.setProduct(product);
        inventoryItem.setAvailability(availability);
        inventoryItem.setCondition(ConditionEnum.NEW_OTHER.toString());
        inventoryItem.setConditionDescription("Brand new unused condition");
        inventoryItem.setPackageWeightAndSize(packageWeightAndSize);
        inventoryItem.setSku("RSUB123");
        HttpResponse response = new eBayAPI().createOrReplaceInventoryItem(inventoryItem, token);
        System.out.println(response);
        assertEquals(204,new eBayAPI().createOrReplaceInventoryItem(inventoryItem, token).getStatusLine().getStatusCode());
    }
    @Test
    public void getInventoryItemTest() throws IOException, URISyntaxException {
        InventoryItem item = new eBayAPI().getInventoryItem("RSUB123", token);
        System.out.println(item);
        assertNotNull(item);
    }
    @Test
    public void getAllInventoryItemsTest() throws IOException, URISyntaxException {
        InventoryItems items = new eBayAPI().getAllInventoryItems(token);
        System.out.println(items);
        assertNotNull(items);
    }
    @Test
    public void deleteInventoryItemTest() throws IOException {
        HttpResponse response = new eBayAPI().deleteInventoryItem("Rolex123",token);
        System.out.println(response);
        assertEquals(204,response.getStatusLine().getStatusCode());
    }

    @Test
    public void createInventoryLocationTest() throws IOException {
        InventoryLocation inventoryLocation = new InventoryLocation();
        Location location = new Location();

        Address address = new Address();
        address.setAddressLine1("2034 Broadway");
        address.setAddressLine2("Apt 5F");
        address.setCity("New York");
        address.setStateOrProvince("NY");
        address.setPostalCode("11101");
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
    public void getInventoryLocationTest() throws IOException, URISyntaxException {
        InventoryLocation location = new eBayAPI().getInventoryLocation("myFirstStore", token);
        System.out.println(location);
        assertNotNull(location);
    }

    @Test
    public void getAllInventoryLocationsTest() throws IOException, URISyntaxException {
        InventoryLocations locations = new eBayAPI().getAllInventoryLocations(token);
        System.out.println(locations);
        assertNotNull(locations);
    }

    @Test
    public void deleteInventoryLocationTest() throws IOException {
        HttpResponse response = new eBayAPI().deleteInventoryLocation("myFirstStore", token);
        System.out.println(response);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void updateInventoryLocationTest() throws IOException, JSONException {
        InventoryLocation inventoryLocation = new InventoryLocation();
        inventoryLocation.setMerchantLocationKey("myFirstStore");
        inventoryLocation.setLocationWebUrl("www.myfirststore.com");
        inventoryLocation.setLocationAdditionalInformation("some location additional information");
        inventoryLocation.setName("myFirstStore");
        inventoryLocation.setPhone("434-353-3252");
        HttpResponse response = new eBayAPI().updateInventoryLocation(inventoryLocation, token);
        System.out.println(response);
        assertEquals(204, response.getStatusLine().getStatusCode());
    }

    @Test
    public void createOfferTest() throws IOException {
        ListingPolicies listingPolicies = new ListingPolicies();
        listingPolicies.setFulfillmentPolicyId("5482884000");
        listingPolicies.setPaymentPolicyId("5482880000");
        listingPolicies.setReturnPolicyId("5482879000");

        PricingSummary pricingSummary = new PricingSummary();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setValue(15.24);
        pricingSummary.setPrice(amount);

        Tax tax = new Tax();
        tax.setApplyTax(true);
        tax.setThirdPartyTaxCategory("Electronics");
        tax.setVatPercentage(10.2);

        Offer offer = new Offer(1, "31387", FormatTypeEnum.FIXED_PRICE.toString(),
                "Some description of the offer", listingPolicies, "EBAY_US",
                "myFirstStore", pricingSummary, "RSUB123");
        offer.setQuantityLimitPerBuyer(1);
        offer.setTax(tax);

        HttpResponse response = new eBayAPI().createOffer(offer, token);
        System.out.println(response);
        assertEquals(201, response.getStatusLine().getStatusCode());

    }

    @Test
    public void getOffersTest() throws IOException, URISyntaxException {
        Offers offers = new eBayAPI().getOffers("RSUB123", token);
        System.out.println(offers);
        assertNotNull(offers);
    }

    @Test
    public void getOfferTest() throws IOException, URISyntaxException {
        Offer offer = new eBayAPI().getOffer("5006343010", token);
        System.out.println(offer);
        assertNotNull(offer);
    }

    @Test
    public void deleteOfferTest() throws IOException {
        HttpResponse response = new eBayAPI().deleteOffer("5006347010", token);
        System.out.println(response);
        assertEquals(204, response.getStatusLine().getStatusCode());
    }

    @Test
    public void publishOfferTest() throws IOException, JSONException {
        String listingId = new eBayAPI().publishOffer("5006348010", token);
        System.out.println(listingId);
    }


}

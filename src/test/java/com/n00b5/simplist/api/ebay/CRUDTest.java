package com.n00b5.simplist.api.ebay;

import com.n00b5.simplist.api.ebay.enums.ConditionEnum;
import com.n00b5.simplist.api.ebay.enums.LengthUnitOfMeasureEnum;
import com.n00b5.simplist.api.ebay.enums.PackageTypeEnum;
import com.n00b5.simplist.api.ebay.enums.WeightUnitOfMeasureEnum;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/5/17
 */
public class CRUDTest {
    String token;
    @Before
    public void setup(){
        token = "v^1.1#i^1#r^0#p^3#I^3#f^0#t^H4sIAAAAAAAAAOVXW2wUVRju9qa1lD4gIF5gGUlUmtmd2bns7oRusr0gK92ysKVApalnz5xpB2Zn1jln2q5BaSqiJEIBgyGWBAR8wIREIxpJaiCKN3zA6AOJGiQhIZiggQAhJmI8s72wrQq9kNjEfZmcc/7b9//ff/b8XE9p2eKty7berPDcV3igh+sp9Hj4cq6stKRqZlHhwyUFXJ6A50DPop7i3qJLSzBIGxllFcIZy8TI2502TKzkNqsZxzYVC2AdKyZII6wQqCSj8QYl4OOUjG0RC1oG443VVTOaAMMCQIEQ5IGERIHumsM2m6xqJiVDHoIAL9CPpAKRnmPsoJiJCTBJNRPg+CDL8SwnN3GCIgUVgfqQQi2MtxnZWLdMKuLjmEguXCWna+fFeudQAcbIJtQIE4lFlyZXRGN19Y1NS/x5tiJDeUgSQBw8elVrqcjbDAwH3dkNzkkrSQdChDHjjwx6GG1UiQ4HM4nwc6nmBQ0KsiqDsKxqUNbuSSqXWnYakDvH4e7oKqvlRBVkEp1k75ZRmo3UBgTJ0KqRmojVed3PSgcYuqYju5qpr4muW52sX8V4k4mEbXXqKlIHkQpCICRIEhMhCNMUIrsNI9JBWWfoJh5yN2hzKNlj/NVapqq7qcPeRovUIBo7GpshMS9DVGiFucKOasSNK19OGs6kGGxxSztYS4d0mG51UZqmw5tb3r0Ow8S4TYV7Ro1UCHCBMAzAcEgIh2AeNdxenzQ9Im6FoomEH6VAlk0DeyMiGQNAxEKaWieNbF1VRDElygBqLK0ZZEVVgmw4LCE2gGQkB4Agcpr4/2MIIbaecggaYcnYgxzUaiYJrQxKWIYOs8xYkdzdM8SJblzNdBCSUfz+rq4uX5fgs+x2f4DjeP/aeEMSdqA0YEZk9bsLs3qOsBBRLawrJJuh0XRT8lHnZjsTEWw1AWySrXGydJ1EbmLbhwk8KsLI2N1/gYpdqNMLpKuPqQGQ0X0ux33QSvstQPvZ3WrLRewdj5A/5WSpfxXZPhsB1TKN7Pj12h3K4UHt8SlhWg3fYDtSGCMe3V6fjIEJ6OhmJ+WyZWcnCHO08gR0AISWY5LJuBtSnYCG5hiabhhuu07GYZ76RMI0gZElOsQjLqfUZdFMJqZOry6L03tEN61GNqmnM4aOCZusWcsCUdJkURQhK6VUMQS5wJRwq6hTh6hNn2bYTccwpoQr3v6PkGivH/oPYTX6o1NCVYc6pxtLeVHlZVlMsUAKpFgRSEFKUS7MhgB98MihoBwOgilhrjV0ejM0Zafbn+AyCxOkTg0afY1OL1DuDTN8wQA5wLFqOEirKoY4NkSHVpbeN6nxQh6zkfek+9t73j96rI4U5H58r+cTrtdznE7mXJBj+SruqdKi1cVFMxisE+TDwFRTVrdPB5oP6+0mnRpt5NuIshmg24WlnviF19dtzhvoD7RyD42M9GVFfHnefM89evukhK+cW8EHOZ6TOUEKCoEW7vHbp8X8nOIHzRudqff6dp/qiy5eoD725tHLu6zTXMWIkMdTUlDc6yk4V1Azo/7dDbe+eTWuWdf3vxV74djB0wPbshfXHXv7xWONlQ9IwYaZ1/Yv//XnhU4Dh2edmPNByZEv0k/ejNz/3fIF8dmtV2fvPnU5vH6D/5waO7ylrexT4aeZZPFC6ZcfpNf61hw/vXngw+e3v3S1bdazh3esnNfd19H9/dn+/uab/ZGDZ5Yd9e575st5xo1Knj1rtl4NV1zf8scbtvzcvj1rWpWSS9/O/3HgxsvXms/vfeLkjPJNm8pqwh8Xfz0Q2luwaO4FbWclPPIKf6X50J6Gpy/uLr9y4s+jc9P8rvmrvjq5piV56pbVotae2RTXPv899dFn7xRGu9OJ9dsfKdpR1Xr2yG9V7/d7t53v3TxYxr8A8oUua2oRAAA=";
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

        assertEquals(204,new eBayAPI().createOrReplaceInventoryItem(inventoryItem, token).getStatusLine().getStatusCode());
    }
    @Test
    public void getInventoryItem() throws IOException {
        InventoryItem item = new eBayAPI().getInventoryItem("Rolex123",token);
        System.out.println(item);
        assertNotNull(item);
    }
    @Test
    public void deleteInventoryItem() throws IOException {
        HttpResponse response = new eBayAPI().deleteInventoryItem("Rolex123",token);
        System.out.println(response);
        assertEquals(204,response.getStatusLine().getStatusCode());
    }
}

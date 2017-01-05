package com.n00b5.simplist.api.ebay;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/5/17
 */
public class CRUDTest {
    @Test
    public void createItem() throws IOException {
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
        Map<String,String> aspects = new HashMap<String, String>();
        aspects.put("Brand", "Rolex");
        aspects.put("Material","Steel");
        Set<String> imageUrls = new HashSet<String>();
        imageUrls.add("http://kasjdfkda");
        imageUrls.add("http://kasjdfkda1234");

        product.setAspects(aspects);
        product.setDescription("Some description");
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
        inventoryItem.setSku("ROlexSKU123");
        String item = "{\n" +
                "    \"product\": {\n" +
                "        \"title\": \"Test listing - do not bid or buy - awesome Apple watch test 2\",\n" +
                "        \"aspects\": {\n" +
                "            \"Feature\" : [\"Water resistance\", \"GPS\"],\n" +
                "            \"CPU\" : [\"Dual-Core Processor\"]\n" +
                "        },\n" +
                "        \"description\": \"Test listing - do not bid or buy \\n Built-in GPS. Water resistance to 50 meters.1 A new lightning-fast dual-core processor. And a display that\\u2019s two times brighter than before. Full of features that help you stay active, motivated, and connected, Apple Watch Series 2 is designed for all the ways you move \",\n" +
                "        \"upc\": [\"888462079525\"],\n" +
                "        \"imageUrls\": [\n" +
                "            \"http://store.storeimages.cdn-apple.com/4973/as-images.apple.com/is/image/AppleInc/aos/published/images/S/1/S1/42/S1-42-alu-silver-sport-white-grid?wid=332&hei=392&fmt=jpeg&qlt=95&op_sharpen=0&resMode=bicub&op_usm=0.5,0.5,0,0&iccEmbed=0&layer=comp&.v=1472247758975\",\n" +
                "            \"http://store.storeimages.cdn-apple.com/4973/as-images.apple.com/is/image/AppleInc/aos/published/images/4/2/42/stainless/42-stainless-sport-white-grid?wid=332&hei=392&fmt=jpeg&qlt=95&op_sharpen=0&resMode=bicub&op_usm=0.5,0.5,0,0&iccEmbed=0&layer=comp&.v=1472247760390\",\n" +
                "            \"http://store.storeimages.cdn-apple.com/4973/as-images.apple.com/is/image/AppleInc/aos/published/images/4/2/42/ceramic/42-ceramic-sport-cloud-grid?wid=332&hei=392&fmt=jpeg&qlt=95&op_sharpen=0&resMode=bicub&op_usm=0.5,0.5,0,0&iccEmbed=0&layer=comp&.v=1472247758007\"\n" +
                "        ]\n" +
                "    },\n" +
                "    \"condition\": \"NEW\",\n" +
                "    \"packageWeightAndSize\": {\n" +
                "        \"dimensions\": {\n" +
                "            \"height\": 5,\n" +
                "            \"length\": 10,\n" +
                "            \"width\": 15,\n" +
                "            \"unit\": \"INCH\"\n" +
                "        },\n" +
                "        \"packageType\": \"MAILING_BOX\",\n" +
                "        \"weight\": {\n" +
                "            \"value\": 2,\n" +
                "            \"unit\": \"POUND\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"availability\": {\n" +
                "        \"shipToLocationAvailability\": {\n" +
                "            \"quantity\": 10\n" +
                "        }\n" +
                "    }\n" +
                "}";

        System.out.println(inventoryItem);
        assertEquals(204,new eBayAPI().createOrReplaceInventoryItem(new ObjectMapper().readValue(item,InventoryItem.class),
                "v^1.1#i^1#f^0#p^3#I^3#r^0#t^H4sIAAAAAAAAAOVXe2wURRjv9toCVqwJoAYRL4sGpNm72dvdu+uGO70+aUJL5QqBGlvmdufaKXu7x85s2xMjpcGaKNoQY6JRTBHiIz6jaAKhKhgjMUZ5/COIwcQElRAVE1I1kDh7fXCtCn2Q2MT75zIz3+v3fb9vdj7QXTRnee/K3sG53Kz8/m7Qnc9xYjGYU1RYepMnf2FhHsgR4Pq77+ou6PH8uILAlJFW1yCStkyCvF0pwyRqdjPCO7apWpBgopowhYhKNTUeq1ulBnxATdsWtTTL4L21lRFeCcFwCCEkAS0cRiDBds0Rm41WhA9ISV2TYAKGknJI1gE7J8RBtSah0KTsHIghAYgCUBoDQBUlFUi+sjKlifeuQzbBlslEfICPZsNVs7p2TqxXDxUSgmzKjPDR2lh1fHWstrKqvnGFP8dWdDgPcQqpQ8auKiwdeddBw0FXd0Oy0mrc0TRECO+PDnkYa1SNjQQzhfCzqZZ0RZckGYRAMKEHk2XXJZXVlp2C9OpxuDtYF5JZURWZFNPMtTLKspFoRxodXtUzE7WVXvfvfgcaOImRHeGrymMb1sar1vDeeEODbXVgHekuUlGSpEBYUhQ+ShFhKUR2C0G0jbHOwCYZdjdkczjZ4/xVWKaO3dQRb71FyxGLHY3PEMjJEBNaba62Y0nqxpUrFxjJZFhscks7VEuHtpludVGKpcObXV67DiPEuEKF60UNXQmG5aRYpidkRgxJyaGG2+tTpkfUrVCsocGPEjAjpKC9CdG0ATUkaCy1TgrZWFdlmXmFWlJgNdMEWVc0gdlFQgAFUTAAGWOT8v+PIZTaOOFQNMqS8QdZqBE+rllp1GAZWMvw40Wyd88wJ7pIhG+jNK36/Z2dnb5OyWfZrf4AAKJ/fd2quNaGUpAflcXXFhZwlrAaYloEqzSTZtF0MfIx52YrH5VsvQHaNFPuZNg6jtzEto4QeEyE0fG7/wKVuFBnFkhXnzADMI19Lsd9mpXyW5D1s7vVko3YOxEhf8LJMP86sn02grplGpmJ67U6jMND2hNTIqwavqF2ZDBGPbq9PhUDk9DBZgfjsmVnJglzrPIkdKCmWY5Jp+JuWHUSGknHSGLDcNt1Kg5z1CcTpgmNDMUaGXU5rS6LpdO1+szqsjp2j2DTqhfiOJU2MKFCvHy9AGUlGZRlWROUhC6HNRCYFm4ddWANteAZht10DGNauOpa/xES6/U9/yGsen9sWqgqUcdMY6ko62IwKCcEqAQSggyVEKMoKBPCkD14guFQsCwEp4W5wsDsZmjMzLSP4EqLUKRPDxp7jc4sUO4NM3LBwGAACHpZiFVVDgMhnJQ0gd03iYlCHreR86T723veP3asjuZlf2IPNwB6uP1sMmfjmyCWgnuKPGsLPDfyBFPkI9DUE1aXD8Okj+BWk02NNvJtQpk0xHZ+EVf3/Y4NW3MG+v4HwW2jI/0cj1icM9+DRVdOCsWSW+eKISAChT1YJSA1gSVXTgvEWwrm95/YV/T7dz21i17949ltj+7qeHHW4xyYOyrEcYV5BT1c3qkLyu7wK85i48jSaPX+iosnqnZ5f+qJ9MHL2y6/ue7wnhfu1HcO/tIsbvQc6d2iNT0f/6365bOLyy94D3pC7Yd3Wm81ek8s/NxfvWX3RzVnOoIle/HR0/Nq1i985I5tnX33Cg/nOc1chffS+1ufe0h555NvSsrPv/1F3lLt3UPLud7tp5/a7+nayDUuGHjsTNuBkm9fEy+ebF5WeuTYx7cf5eZ/2tZ76WD3r5F5VTiw4dyfgyveSx+rKdj6tRk9/jNcfPyltba2/VDYKt1YfO5Uy+y9nX07bn7mq2X7ms/GPE+//tmSG84PFJ9c8IR19w+V7X0fHkg8sCDePrtt8Nzs7kX3ffDkQGjzl5E3NgeGyvgXLVjYPWoRAAA="));


    }
}

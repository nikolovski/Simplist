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
        token = "v^1.1#i^1#r^0#p^3#I^3#f^0#t^H4sIAAAAAAAAAOVXe2wURRjv0QdUXjEhVhT1WEQDuHezt4+7LvTkSiFtoO3BlQZaa53dnWsH9nYvO7NtT2OoxZAohqBGSIhEov6BSiUQQQg+AsaIIRr4R5QQQiQCEgOaSAgJqLPXB9eq0AeJTbx/LjPzvX7f9/u+nQGdRcVzN1RuuDbZN37cjk7QOc7nEyaC4qLCeVPyxz1QmAdyBHw7Oh/tLOjKv7iAwJSZVlcgkrYtgvwdKdMianazjHMdS7UhwUS1YAoRlepqIla9TA0FgJp2bGrrtsn5qyrKuLCsgYiuKwpUND0simzX6rNZZ5dxchKUJoUk0mTZEEO6wM4JcVGVRSi0aBkXAkKYBwIPlDpBUgVFFaWAEg41cP565BBsW0wkALhoNlw1q+vkxHr7UCEhyKHMCBetii1J1MaqKhbX1C0I5tiK9uYhQSF1ycDVIttA/npouuj2bkhWWk24uo4I4YLRHg8DjaqxvmBGEH421TKIJMOaIkBdB4JglN6VVC6xnRSkt4/D28EGn8yKqsiimGbulFGWDW0N0mnvqoaZqKrwe3/LXWjiJEZOGbe4PLZ6ZWLxCs6fiMcduw0byPCQCqIohiKiLHNRighLIXKaCaKtjHUmtkivux6bvcke5G+RbRnYSx3x19i0HLHY0eAMgZwMMaFaq9aJJakXV66c0pdJWWzwSttTS5e2Wl51UYqlw59d3rkOfcS4RYW7RQ1JRkhUgBARoCEaRiiHGl6vj5geUa9CsXg8iDSY4VPQWYto2oQ64nWWWjeFHGyokqRJCtSTPKuZzkuGrPOlpTLiQ0hBSgiKEkhK/z+GUOpgzaWonyWDD7JQy7iEbqdR3DaxnuEGi2RnTy8nOkgZ10ppWg0G29vbA+1iwHZagiEAhOCq6mUJvRWlINcvi+8szOMsYXXEtAhWaSbNoulg5GPOrRYuKjpGHDo0U+5m2DqBvMS29BF4QITRwbv/ApV4UMcWSE+fMAMwjQMexwO6nQrakPWzt9Wcjdg/FKGg5maYfwM5AQdBw7bMzND1WlzG4R7toSkRVo1ATzsyGP0evV4fiYFh6GCrjXHZdjLDhDlQeRg67GtnuxYdibte1WFoJF0ziU3Ta9eROMxRH06YFjQzFOuk3+WouiyWTlcZY6vLqtkcwZZdwydwKm1iQvlE+SoeSnJSkSRJ52XNkCI6CI0Kt4HasI6a8RjDbrmmOSpc1S3/CIn1+jv/IayaYGxUqCpQ21hjqSAZgqJIGg/lkMZLUA4zioJSPgLZhUeJhJXSMBwV5kUmZpOhLjPWPoKVNqHIGB00dhsdW6C8CdM3YKASArxRGmZVlSKAjyRFnWfzRhsq5EEbOVe6v93ngwOf1dG87E/o8n0KunwH2cschAEvzANzivJXFuRP4gimKECgZWh2RwDDZIDgFou9Gh0UWIsyaYidcUW+6nOvrF6X86Df0QTu73/SF+cLE3Pe92DGrZNCYWrJZCEMBKAIkqCIUgOYdeu0QLivYNoXDZXr+RunDsxyD/km3ZywZue0zRKY3C/k8xXmFXT58p5b2ChacMvRpeF3n/h65m+7a+4195ze8BiYf/XctvET3ox+9/FMrbPk1MkLeVtLLnBS+enNLz/1TPrYkwWHZux0fVPNiLv9vabmE8c+6q548eyavRfmTD+/Nn7iSuM9/JdV36x+xNr/2vTryqV5+9e9Xfvt7wvf+r7tWGPTgWvLHz9lHfdNPXuU+3zj5Qef5maH917ZN158vl0z9onSJ0eWHqzcRRfsbvXP/7N+Pdz08/r4D02Xfpqu/7Gu5cYe9JB9tLvzs4dvzN5aUPxSavvckqttPyq/+l74JYoO726sES4LEy+33Tx+0f/Gxk1Tzhze9uqzZ+ac7J50ZNeW97s/PL/fufLBysTrM6U9X9Xb13vK+Be83OghahEAAA==";
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
    public void deleteInventoryItem() throws IOException {
        HttpResponse response = new eBayAPI().deleteInventoryItem("Rolex123",token);
        System.out.println(response);
        assertEquals(204,response.getStatusLine().getStatusCode());
    }
}

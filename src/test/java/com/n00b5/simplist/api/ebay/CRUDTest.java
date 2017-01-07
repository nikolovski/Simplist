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
 * @author Martino Nikolovski
 * @date 1/5/17
 */
public class CRUDTest {
    String token;
    @Before
    public void setup(){
        token = "v^1.1#i^1#f^0#p^3#I^3#r^0#t^H4sIAAAAAAAAAOVXW2wUVRjuttuSUi6J4R6N66AJSGb3zM7MbndgV7ctSKGXhW2bgiKemTmzPXZ2ZjNnpu0alNogKlWDGn3AB4gSBTQiBqIV+6TBRIygRmODgYcmhgcvPIkVLJ7ZXthWhV5IbGJfmjnnv33f//1nzwGdJaX37l6/+/Jcz6zCA52gs9Dj4cpAaUnxqnlFhcuKC0CegedA592d3q6ii2sITOsZaTMiGdMgyNeR1g0i5RajjGMZkgkJJpIB04hItiIl47U1UtAPpIxl2qZi6oyvuirKyDIMy6oqRFBIFDUtTFeNkZgNZpTh1PKIhngZhQWO41We7hPioGqD2NCwo0wQcGEWcCwINXCixAclQfBHhMhWxteELIJNg5r4ARPLlSvlfK28Wm9cKiQEWTYNwsSq4+uS9fHqqrV1DWsCebFiwzwkbWg7ZOxXpakiXxPUHXTjNCRnLSUdRUGEMIHYUIaxQaX4SDFTKD9HtaggTgYqCKEQVEUO3BIq15lWGto3rsNdwSqr5UwlZNjYzt6MUcqG/ChS7OGvOhqiusrn/tvkQB1rGFlRZm1FfEtjcu1mxpdMJCyzDatIdZFyPM8Hy3lRZGI2IpRCZG0nyG6hqtOxQYbTDcUcJntcvkrTULFLHfHVmXYForWj8QwF8xiiRvVGvRXXbLeufLvwCJM82Oq2dqiXjt1iuN1FaUqHL/d58z6MCOO6FG6VNHghpGhcSBQEORgReZgnDXfWpyyPmNuheCIRQDLMsmlotSI7o0MFsQql1kkjC6s0jiyEoKKxtGcKK6iiwkYiImKDiCo1CHkBaML/TyG2bWHZsdGoSsZv5KBGmaRiZlDC1LGSZcab5M6eYU10kCjTYtsZKRBob2/3t/N+00oFggBwgebamqTSgtK07SO2+ObGLM4JVkHUi2DJzmZoNR1UfDS5kWJivKUmoGVnK5ws/U4il9jUiIDHVBgbv/ovUIkLdWaBdP0JDQAz2O9q3K+Y6YAJ6Ty7S9tzFfsmYhSQnSzNryLLbyGomoaenbhfyqEaHvKemBOh3fAPjSOFMZrRnfWpBJiEDzbaqJZNKztJmGOdJ+EDFcV0DHsq6YZdJ+GhObqGdd0d16kkzHOfTJkG1LM2VshoymlNWTyTqVZn1pTV0nMEG2Ydm8TpjI6JzSYrmlkoiFpIEASFFWVVKFdAcFq4VdSGFbQdzzDshqPr08JVm/pHSHTW3/gPYdUF4tNCVYXaZppKOUHlQiFBZqEYlFkBimEqURBhyyG98ITKw6FIGE4Lc6WO6cnQkJ1pP4LrTWIjdXrQ6G10ZoFyT5iRAwaGgoBVI2HaVaEcsOUar7D0vJEnCnncQt6V7m/3+cDYZ3WsIPfHdXl6QZenh77MQRiw3CqwsqSo0Vs0hyHYRn4CDVU2O/wYan6CUwZ9NVrI34qyGYitwhJPbf9zW3bmPegPbANLRp/0pUVcWd77Htx+faeYm794LhcGHAhxIh8UhK1g+fVdL7fIu+Dt17ufmXVp27KrRxf9OLtvMHU43VsH5o4aeTzFBd4uT4Fw8tS+otCOQ7s6vjNx7JvGHqUkfOKeu3Bi9ZKN85raoj2xxJGW5peDO1b23hflj/YUDFxrPDn4vld5oOzU8bbHPvjq9886Wz3mt30Dl99yFl4K9A0+9drTt+23/vj4nFJy4sGDZccOHjq8ZUXvhX1w+YBHydzfN/jixZ39v81Z8CQush66sLrxjveWz0+UbTjR1HFlSephlvF+/e6KPV9sPL30omieXtpvvtN25s0XnC+PPP5n96ZH9nz+0c7zA78U1OxiP/n5iZo7zVdI5YYVe/e+dKH7zNn2DeKHvoO+K1U7PoWg4fzV+udf7f5BPef5Xis91srN1vq8i49fW7QwHiluXlNwdn//T7+SZ4fa+BfR25AKahEAAA==";
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

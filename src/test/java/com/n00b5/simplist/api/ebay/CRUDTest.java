package com.n00b5.simplist.api.ebay;

import com.n00b5.simplist.api.ebay.enums.ConditionEnum;
import com.n00b5.simplist.api.ebay.enums.LengthUnitOfMeasureEnum;
import com.n00b5.simplist.api.ebay.enums.PackageTypeEnum;
import com.n00b5.simplist.api.ebay.enums.WeightUnitOfMeasureEnum;
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
        token = "v^1.1#i^1#p^3#I^3#r^0#f^0#t^H4sIAAAAAAAAAOVXW2wUVRjutl1IQagkXEQw2Yx4qWV2z8zOzu4O7Or2QroJLYUt5RKgnJ05uz12dmYz50zbVQJNH5qYmPhkghGkImIxhCBGFC+YCEoDvBAVFCmJ0UQfjIrh8qLGM9sL26rQC4lN3JfNOee/ff///f+cA7pmlD3RU9dza45rZnFvF+gqdrmE2aBshrtybknxg+4iUCDg6u1a1lXaXfLjSgIzelZZh0jWNAjydGZ0gyj5zQhnW4ZiQoKJYsAMIgpVlUSsfrUieoGStUxqqqbOeeI1EU4LiMgvqgKSkRYWhBTbNYZtNpkRLhQCIAyDQPbDQCApBNk5ITaKG4RCg0Y4EQhBHgg8kJuAoIiiIoS8QRDazHmakUWwaTARL+Ci+XCVvK5VEOudQ4WEIIsyI1w0HluVWBOL19Q2NK30FdiKDuUhQSG1yehVtakhTzPUbXRnNyQvrSRsVUWEcL7ooIfRRpXYcDCTCD+falEUgkFBDUtaKhzwi8l7kspVppWB9M5xODtY41N5UQUZFNPc3TLKspF8Gql0aNXATMRrPM7fWhvqOIWRFeFqq2Kb1idq13GeRGOjZbZjDWkOUsHv94shfyDARSkiLIXIaiGItjLW6dggQ+4GbQ4le4y/atPQsJM64mkwaRVisaOxGRIKMsSE1hhrrFiKOnEVyvmHMimHpc1OaQdradNWw6kuyrB0ePLLu9dhmBi3qXCvqCELASkIwlIYin45BaUCaji9Pml6RJ0KxRobfSgJc3wGWm2IZnWoIl5lqbUzyMKaIklJSYZqimc1U3lJC6h8OBxAvMhmgixCvwRS0v+PIZRaOGlTNMKSsQd5qBEuoZpZ1GjqWM1xY0Xys2eIE50kwrVSmlV8vo6ODm+H32taaZ8IgODbWL86obaiDORGZPHdhXmcJ6yKmBbBCs1lWTSdjHzMuZHmon5La4QWzVXZObZOICex6WECj4owOnb3X6ASB+r0AunoE2YAZrHX4bhXNTM+E7J+drZa8hF7xiPkS9o55l9DltdCUDMNPTd+vbTNODyoPT4lwqrhHWxHBmPEo9PrkzEwAR1stDMum1ZugjBHK09AB6qqaRt0Mu6GVCegkbL1FNZ1p10n47BAfSJhGlDPUaySEZdT6rJYNhvXpleX1bM5gg2zgU/gTFbHhPKJqo08lAIpWZIklQ8kNSmkAnFKuDXUjlXUgqcZdsPW9Snhqk//IyTW66/9h7AafLEpoapB7dONpYKkCbIsJXkYEJO8BANBRlEQ5kOQXXjkUFAOB+GUMFfrmE2Gptx0+wjWmYQibWrQ2G10eoFyJszwgIGyCHgtHGRVlUKAD6X8Ks/mTXK8kMdsFFzp/naf941+VkeL8j+h2/UR6HadYC9zEAS8UAkqZpSsLy25jyOYIi+BhpY0O70YprwEpw32arSQtw3lshBbxTNc9d89v2lXwYO+dyt4YORJX1YizC5434Olt0/cQvmiOUIQCEAGAns+hjaDh2+flgoLS+f3fP7h/ph7h9FCSyNXK3yHj7+/dweYMyLkcrmLSrtdRVqsvKm7b1u0/8kjnwzsPNXvaqu8vsW6WA66nrm46Fd86MWBb5u3n6/p4PWPYWT5sg2Xi75smH8hXHyga0n3Yz/XLtjgy/YdS7f17d8R7M0pj8670dN47Ifiq8utFf3H62/sennlLGnWZ3teHzgt/3LtzPfzzp6kq2qPLDkfOrdr56f9WzyhzuRTr6zd3USu775Z/ufp7SsOz92659WZzVLlF1cunSuuGHjOezJdd3+ZdP1s/OAfb19zq0teevOho/HHb37Q9vuZC669fb2JJq4MH/jp2DfvNaw+heeG37ky82Jv1eKKfQvc1e7Lp+u+JouXvnHpDN4GDh18d/8jL9yqhG/tW7bw+NFnD3114rfBMv4FoGyWKmoRAAA=";
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
        inventoryItem.setSku("Rolex123");

        assertEquals(204,new eBayAPI().createOrReplaceInventoryItem(inventoryItem, token));
    }
    @Test
    public void getInventoryItem() throws IOException {
        assertEquals(200, new eBayAPI().getInventoryItem("Rolex123",token));
    }
}

package com.n00b5.simplist.api.ebay;

import com.n00b5.simplist.api.ebay.enums.ConditionEnum;
import com.n00b5.simplist.api.ebay.enums.LengthUnitOfMeasureEnum;
import com.n00b5.simplist.api.ebay.enums.PackageTypeEnum;
import com.n00b5.simplist.api.ebay.enums.WeightUnitOfMeasureEnum;
import com.n00b5.simplist.api.ebay.inventory.*;
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
        token = "v^1.1#i^1#f^0#p^3#I^3#r^0#t^H4sIAAAAAAAAAOVXbWxTVRhe121AxocRBEImNpcFQXLb09vb2/a6lnRfrMC6jg5kIC6n9567Xbi9t7nn3G0NEuZEpqgJ+ks0JHwsIBpIjIoQIUgi0fBDAz9EiIIJRPkh0ZAQEjTqud1XNxX2QeISmybNOef9et73ed+eA7pKpj21s27n3RmOKYX7ukBXocPhLQXTSoqXzXQWLiguAHkCjn1d5V1F3c6bFRimtYy4BuGMoWPk6kxrOhZzm2HGMnXRgFjFog7TCItEEpPR+tUi5wZixjSIIRka44pVh5mUEIAy8gs8L3Ah2Reiu/qAzSYjzAQ46JMQxwlIUGSBl+k5xhaK6ZhAnYQZDngDLPCyINAEBJEP0a+b40MbGNc6ZGLV0KmIGzCRXLhiTtfMi/X+oUKMkUmoESYSi9YmG6Kx6pp4U4Unz1akPw9JAomFh6+qDBm51kHNQvd3g3PSYtKSJIQx44n0eRhuVIwOBDOO8HOpluVAKISgIiMg+xUl+FBSWWuYaUjuH4e9o8qskhMVkU5Ukn1QRmk2UpuRRPpXcWoiVu2yfxotqKmKiswwU1MZbV6brFnDuJKJhGm0qzKSbaRen8/HBX1+PxMhCNMUIrMFI9JGWaepOu5312ezP9kj/FUZuqzaqcOuuEEqEY0djcyQLy9DVKhBbzCjCrHjypcLDmTS599gl7avlhZp0+3qojRNhyu3fHAdBogxRIWHRY2QwMkylBSvoHABDuUzw+718bIjYhcomkh4UApm2TQ0tyCS0aCEWIlm1kojU5VFnk/xAvXN0pJJLC/7JTYU8iOWQ7TnaffzQOH/fwQhxFRTFkGDJBl5kIMaZpKSkUEJQ1OlLDNSJDd6+inRicNMGyEZ0ePp6Ohwd/jchtnq4QDwetbXr05KbSgNmUFZ9cHCrJrjq0TJQuVFks3QaDop96hzvZWJ+Ew5AU2SrbSydJ1EdmJbB/g7LMLIyN1/gYptqJMLpK2PqQGYUd02x92SkfYYkLazvdWSi9g1GiFPyspS/zIy3SaCsqFr2dHrtVqUw33ao1PCtBruvnakMIY82r0+DgNjcKrq7ZTLhpkdI8zhymPQgZJkWDoZj7t+1TFoKJamqJpmt+t4HOapjyVMHWpZokp40OWEuiyaycTkydVl9XSOqLoRZ5NqOqOpmLDJyvUs5P0KvTbyEutPyXxQAtyEcMuoXZVQizrJsOuWpk0IV33rP0OivX7gv4MV90QnhKoatU82lnp52SsIfIqFfi7F8tAfoBQFITYI6YVHCAaEUABOCHOVptLJ0JSdbH+CdQYmSJ4YNHoZnVyg7AkzMGCgwAFWDgVoVfkgYIOKT2LpvEmNFvKIjbwr3d+u857hr+pIQe7j7XacBt2Ok/RhDgKA9S4DS0uca4uc0xmsEuTGUJdTRqdbhYobq606fTSayL0FZTNQNQtLHPXXX2venvee37cJzB980U9zekvznvegbOik2Dtr3gxvAHipU4EP0XssWDR0WuSdWzTnq+fOKWVXu53WN4cr2qe/CjbC5XPBjEEhh6O4oKjbUdADju9ecqBtF/niXkdp+b23tr554euZm1b1lFyc+cZvCxclep/5s6p5zs0/Pg7cXHzJ99ILPx/88qDn0713kXCx9uivt6cWO+8cOTs9sfv57U98Vvj5nSs9r1z7/nTjydmfbC7bf+zCSp3bey52bF2Gff/R34vS73w06+z82eXbbnyQWXrxRFNDY+uLP5xY8chmvONdecn1t1/f+l5Rl1C3Z+/cDVOjy3sPXV58/OmfzpzeeGp/eEXNkqy/ceUq6+qNeb07FzY//mPFjtJtu0BP9uVb5Ve+2yKfv3ao+vzlo78oa7Y7a+sWlO6+FY9zl75d5l54+Ej8+pkP94i3W5IrZrcxZb3LN065vbjm2cc2nXqyyeor41/gawcXaREAAA==";
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
}

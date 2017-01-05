package com.n00b5.simplist.api.ebay;

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
        Map<String,String[]> aspects = new HashMap<String, String[]>();
        String brand[] = new String[1];
        brand[0] = "Rolex";
        String material []= new String[1];
        material[0] = "Steel";

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
        inventoryItem.setSku("ROlexSKU123");

        System.out.println(inventoryItem);
        assertEquals(204,new eBayAPI().createOrReplaceInventoryItem(inventoryItem,
                "v^1.1#i^1#r^0#p^3#I^3#f^0#t^H4sIAAAAAAAAAOVXW2wUVRju9iYNtoRKrBEl60BAILN7ZndmLwO7ZHtB1tDrFihEKWdnzrRDZ2fGOWfarqI2VQgqStREggZBiAqChidRQ3kgRC2BqIkYNUbBG3h9QA360OiZ7bZsq0IvJDZxXzbnnP/2/f/3/3MO6CkuWbRlxZZLpa7r8vf0gJ58l4ubDkqKixaXFeTfXJQHcgRce3rm9RT2FlxYimFKM8UmhE1Dx8jdndJ0LGY2I4xt6aIBsYpFHaYQFokkJmK1K0WfB4imZRBDMjTGHa+OMLySROGALxRGPIS8X6C7+pDNZiPChJL+YDgkBwTA85DzyfQcYxvFdUygTiKMD3BBFnAsEJp9nCgERRD2UNl1jHs1srBq6FTEA5hoJlwxo2vlxHrlUCHGyCLUCBONx5Yn6mPx6pq65qXeHFvRbB4SBBIbj1xVGTJyr4aaja7sBmekxYQtSQhjxhsd9DDSqBgbCmYC4WdSLShhJAMByoIsJXkBXpNULjesFCRXjsPZUWVWyYiKSCcqSV8tozQbyY1IItlVHTURr3Y7f4021FRFRVaEqamMrV2VqGli3ImGBsvoVGUkO0g5v9/vC/kFgYkShGkKkdWKEWmnrNNUHWfdDdrMJnuUvypDl1UnddhdZ5BKRGNHozPE52SICtXr9VZMIU5cuXL+bCb5UHidU9rBWtqkXXeqi1I0He7M8up1GCLGZSpcK2qEBIgA7TMlKXOhAOByqOH0+oTpEXUqFGto8KIkTLMpaHUgYmpQQqxEU2unkKXKIs8n+QCUFJbWTGJ5WZDYcFhArA8FUMAH/TxQ+P8fQwix1KRN0DBLRh9koEaYhGSYqMHQVCnNjBbJzJ4sJ7pxhGknxBS93q6uLk+X32NYbV4fAJy3pXZlQmpHKToRhmTVqwuzaoawEqJaWBVJ2qTRdFPyUed6GxP1W3IDtEi60k7TdQI5iW0bIvCICKOjd/8FKnagTi2Qjj6mBqCpehyOeyQj5TUg7WdnqzUTsXssQt6knab+ZWR5LARlQ9fSY9drsymHB7XHpoRpNTyD7UhhDHt0en0iBsaho+qdlMuGlR4nzJHK49CBkmTYOpmIu6zqODQUW1NUTXPadSIOc9THE6YOtTRRJTzsclJdFjPNuDy1uqyWzhFVN+rYhJoyNRUTNlHZwkJeUAI8z0uskJT5kAR8k8Ito05VQq3qFMOu25o2KVy1bf8Iifb6vv8QVp03NilU1ahzqrGU42UuEOCTLBR8SZaHQpBSFITZEKQXnkAoGAgH4aQwV2kqnQzN6an2EVxhYILkyUGjt9GpBcqZMEMDBgZ8gJXDQVpVPgTYkOKXWDpvkmOFPGoj50r3t/u8d+SzOpqX+XG9rj7Q63qTvsxBELDcYrCwuGBVYcH1DFYJ8mCoy0mj26NCxYPVNp2+Gi3k6UBpE6pWfrGr9qttax/MedDvuRvcNPykLyngpue878Etl0+KuBkVpVwQcEDwcUIQhNeBuZdPC7kbC2cd2dJ3/FWu/NnG90/O4CpPlpC9JZ+A0mEhl6sor7DXlbc9/IOS+GX2/ucjB6t79vYfrLpN/3brQ2sq+u/LO33x3G+zzA63948ZRtUzC1p+emf/d8qB9NP87PWp7cayTeL300Jb718Tqjr7sLb8x/hOJbCxP/7FY1+vWHKiTJmzZOa+9bvuKG967UB59fGj783T0q+/XLHt0dipO0+c2fXkp30lh4tvFYW8011vdwe+vH2+d5P3fHwR2v5Zx5HQ511vDZz7ZqCvpjxRsWPnBxtKGs9zjxwuPfPG5pYPN889tmPapd/LXti9a+ErW8UXTx2tX7ByYNkTFU9tuOfPX88emXXvQNnHF1vn7H7cVVXf+G7TMXNuw+L5Zo1x6FBh/3PzXloWf2DHhTmtM+/6OV7w0Q3gzGAZ/wIHH7NZahEAAA=="));


    }
}

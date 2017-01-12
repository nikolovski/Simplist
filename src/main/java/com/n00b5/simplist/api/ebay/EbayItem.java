package com.n00b5.simplist.api.ebay;

import com.n00b5.simplist.api.ebay.enums.*;
import com.n00b5.simplist.api.ebay.inventory.*;
import com.n00b5.simplist.api.ebay.offer.*;
import org.apache.http.HttpResponse;
import org.json.JSONException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: simplist
 *
 * @author d4k1d23
 * @date 1/12/17
 */
@Entity
@Table(name = "EBAY_ITEM")
public class EbayItem {
    /**
     * Important properties
     */
    @Column
    private String sku;
    @Column
    private String merchantLocationKey;
    @Column
    private String marketplaceId;
    @Column
    private String offerId;
    @Id
    private String listingId;

    //Mandatory fields
    @Column
    private String productTitle;
    @Column
    private String productDescription;
    @Column
    private double currencyValue;
    @Column
    private String productBrand;
    @Column
    private String[] productImageUrls;
    @Column
    private String productMpn;
    @Column
    private int dimHeight;
    @Column
    private int dimWeight;
    @Column
    private int weightValue;
    @Column
    private String weightUnit;
    @Column
    private int dimLength;
    @Column
    private int dimWidth;
    @Column
    private String dimUnit;
    @Column
    private String packageType;
    @Column
    private int availability;
    @Column
    private String condition;
    @Column
    private String conditionDescription;

    @Column
    private String fulFillmentPolicyId;
    @Column
    private String paymentPolicyId;
    @Column
    private String returnPolicyId;
    @Column
    private String currency;
    @Column
    private String taxCategory;
    @Column
    private double vatPercentage;
    @Column
    private String categoryId;
    @Column
    private String formatType;
    @Column
    private String listingDescription;
    @Column
    private String status;

    public EbayItem() {
    }

    public void createSimpleList(String token) throws IOException, URISyntaxException, JSONException {
        InventoryItem inventoryItem =
                new InventoryItem();
        Product product =
                new Product();
        PackageWeightAndSize packageWeightAndSize =
                new PackageWeightAndSize();
        Availability availability =
                new Availability();
        ShipToLocationAvailability shipToLocationAvailability =
                new ShipToLocationAvailability();
        Dimension dimension = new Dimension();
        Weight weight = new Weight();
        Map<String,String[]> aspects = new HashMap<String, String[]>();
        aspects.put("Brand",new String[]{"Generic"});

        product.setAspects(aspects);
        product.setTitle(productTitle);
        product.setDescription(productDescription);
        product.setImageUrls(productImageUrls);
        product.setBrand(productBrand);
        product.setBrand(productMpn);

        dimension.setHeight(1);
        dimension.setWidth(1);
        dimension.setLength(1);
        dimension.setUnit(LengthUnitOfMeasureEnum.INCH.toString());

        weight.setUnit(WeightUnitOfMeasureEnum.POUND.toString());
        weight.setValue(1);

        packageWeightAndSize.setPackageType(PackageTypeEnum.MAILING_BOX.toString());
        packageWeightAndSize.setDimension(dimension);
        packageWeightAndSize.setWeight(weight);

        shipToLocationAvailability.setQuantity(1);
        availability.setShipToLocationAvailability(shipToLocationAvailability);

        inventoryItem.setProduct(product);
        inventoryItem.setCondition(ConditionEnum.NEW_OTHER.toString());
        inventoryItem.setConditionDescription("Brand new unused condition");
        inventoryItem.setPackageWeightAndSize(packageWeightAndSize);
        inventoryItem.setSku(sku);
        new eBayAPI().createOrReplaceInventoryItem(inventoryItem, token);

        ListingPolicies listingPolicies = new ListingPolicies();
        listingPolicies.setFulfillmentPolicyId("5482884000");
        listingPolicies.setPaymentPolicyId("5482880000");
        listingPolicies.setReturnPolicyId("5482879000");

        PricingSummary pricingSummary = new PricingSummary();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setValue(currencyValue);
        pricingSummary.setPrice(amount);
        Tax tax = new Tax();
        tax.setApplyTax(true);
        tax.setThirdPartyTaxCategory("Electronics");
        tax.setVatPercentage(10.2);

        Offer offer = new Offer();
        offer.setAvailableQuantity(1);
        offer.setCategoryId("31387");
        offer.setFormat(FormatTypeEnum.FIXED_PRICE.toString());
        offer.setListingDescription(listingDescription);
        offer.setListingPolicies(listingPolicies);
        offer.setMarketplaceId("EBAY_US");
        offer.setMerchantLocationKey("myFirstStore2");
        offer.setPricingSummary(pricingSummary);
        offer.setSku(sku);
        offer.setQuantityLimitPerBuyer(1);
        offer.setTax(tax);
        HttpResponse response = new eBayAPI().createOffer(offer, token);
        Offers offers = new eBayAPI().getOffers(sku, token);
        offerId = offers.getOffers()[0].getOfferId();
        status = offers.getOffers()[0].getStatus();
        listingId = new eBayAPI().publishOffer(offerId, token);
    }

    public void deleteOffer(String token) throws IOException {
        new eBayAPI().deleteOffer(offerId, token);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMerchantLocationKey() {
        return merchantLocationKey;
    }

    public void setMerchantLocationKey(String merchantLocationKey) {
        this.merchantLocationKey = merchantLocationKey;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String[] getProductImageUrls() {
        return productImageUrls;
    }

    public void setProductImageUrls(String[] productImageUrls) {
        this.productImageUrls = productImageUrls;
    }

    public String getProductMpn() {
        return productMpn;
    }

    public void setProductMpn(String productMpn) {
        this.productMpn = productMpn;
    }

    public int getDimHeight() {
        return dimHeight;
    }

    public void setDimHeight(int dimHeight) {
        this.dimHeight = dimHeight;
    }

    public int getDimWeight() {
        return dimWeight;
    }

    public void setDimWeight(int dimWeight) {
        this.dimWeight = dimWeight;
    }

    public int getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(int weightValue) {
        this.weightValue = weightValue;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public int getDimLength() {
        return dimLength;
    }

    public void setDimLength(int dimLength) {
        this.dimLength = dimLength;
    }

    public int getDimWidth() {
        return dimWidth;
    }

    public void setDimWidth(int dimWidth) {
        this.dimWidth = dimWidth;
    }

    public String getDimUnit() {
        return dimUnit;
    }

    public void setDimUnit(String dimUnit) {
        this.dimUnit = dimUnit;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public String getFulFillmentPolicyId() {
        return fulFillmentPolicyId;
    }

    public void setFulFillmentPolicyId(String fulFillmentPolicyId) {
        this.fulFillmentPolicyId = fulFillmentPolicyId;
    }

    public String getPaymentPolicyId() {
        return paymentPolicyId;
    }

    public void setPaymentPolicyId(String paymentPolicyId) {
        this.paymentPolicyId = paymentPolicyId;
    }

    public String getReturnPolicyId() {
        return returnPolicyId;
    }

    public void setReturnPolicyId(String returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public double getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(double vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

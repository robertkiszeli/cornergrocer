package com.robertkiszelirk.cornergrocer;

// THIS IS THE CLASS THAT STORES THE BASE PARAMETERS FOR ONE PRODUCE
class BaseProduce {

    // THE NAME OF THE PRODUCE
    private String produceName;
    // THE SELLING PRICE WITH CURRENCY AND UNIT
    private String sellingPrice;
    // THE NAME OF THE PICTURE
    private String picture;

    BaseProduce(String sProduceName, String sSellingPrice, String sPicture){
        // SETTING THE PARAMETERS
        produceName = sProduceName;
        sellingPrice = sSellingPrice;
        picture = sPicture;

    }

    // GET THE PRODUCE  NAME
    String getProduceName() {return produceName;}
    // GET SELLING PRICE
    String getSellingPrice() {
        return sellingPrice;
    }
    // GET PICTURE NAME
    String getPicture() {
        return picture;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.member.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thorsten
 */
@XmlRootElement
public class Discounts
{
    private String price;

    private String number_of_passes;

    private String price_in_cents;

    private String currency;

    private String tax_rate;
    

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getNumber_of_passes ()
    {
        return number_of_passes;
    }

    public void setNumber_of_passes (String number_of_passes)
    {
        this.number_of_passes = number_of_passes;
    }

    public String getPrice_in_cents ()
    {
        return price_in_cents;
    }

    public void setPrice_in_cents (String price_in_cents)
    {
        this.price_in_cents = price_in_cents;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    public String getTax_rate ()
    {
        return tax_rate;
    }

    public void setTax_rate (String tax_rate)
    {
        this.tax_rate = tax_rate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", number_of_passes = "+number_of_passes+", price_in_cents = "+price_in_cents+", currency = "+currency+", tax_rate = "+tax_rate+"]";
    }
}

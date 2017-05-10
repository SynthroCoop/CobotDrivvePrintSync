/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.print.user.sync.model;

/**
 *
 * @author thorsten
 */
public class Extras
{
    private String price;

    private String name;

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

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
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
        return "ClassPojo [price = "+price+", name = "+name+", price_in_cents = "+price_in_cents+", currency = "+currency+", tax_rate = "+tax_rate+"]";
    }
}

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
public class Extras
{
    private String price;

    private String name;

    private String price_in_cents;

    private String currency;

    private String tax_rate;
    
    private String accounting_code;
    
    private String id;
    

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

    /**
     * @return the accounting_code
     */
    public String getAccounting_code() {
        return accounting_code;
    }

    /**
     * @param accounting_code the accounting_code to set
     */
    public void setAccounting_code(String accounting_code) {
        this.accounting_code = accounting_code;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}

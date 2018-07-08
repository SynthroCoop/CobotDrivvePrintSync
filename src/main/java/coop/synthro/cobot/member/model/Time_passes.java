/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.member.model;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thorsten
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class Time_passes
{
    private String included_per_timespan;

    private String id;

    private String price;

    private String timespans;

    private Discounts[] discounts;

    private String name;

    private String included_timespan;

    private String price_in_cents;

    private String currency;

    private String tax_rate;
    
    private String accounting_code;
    
    private Parsed_Timespans[] parsed_timespans;

    public String getIncluded_per_timespan ()
    {
        return included_per_timespan;
    }

    public void setIncluded_per_timespan (String included_per_timespan)
    {
        this.included_per_timespan = included_per_timespan;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getTimespans ()
    {
        return timespans;
    }

    public void setTimespans (String timespans)
    {
        this.timespans = timespans;
    }

    public Discounts[] getDiscounts ()
    {
        return discounts;
    }

    public void setDiscounts (Discounts[] discounts)
    {
        this.discounts = discounts;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getIncluded_timespan ()
    {
        return included_timespan;
    }

    public void setIncluded_timespan (String included_timespan)
    {
        this.included_timespan = included_timespan;
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
        return "ClassPojo [included_per_timespan = "+included_per_timespan+", id = "+id+", price = "+price+", timespans = "+timespans+", discounts = "+discounts+", name = "+name+", included_timespan = "+included_timespan+", price_in_cents = "+price_in_cents+", currency = "+currency+", tax_rate = "+tax_rate+"]";
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
     * @return the parsed_timespans
     */
    public Parsed_Timespans[] getParsed_timespans() {
        return parsed_timespans;
    }

    /**
     * @param parsed_timespans the parsed_timespans to set
     */
    public void setParsed_timespans(Parsed_Timespans[] parsed_timespans) {
        this.parsed_timespans = parsed_timespans;
    }


}
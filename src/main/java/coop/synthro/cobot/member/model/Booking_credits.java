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
public class Booking_credits
{
    private Resources[] resources;

    private String hours;

    private String price_per_hour;

    public Resources[] getResources ()
    {
        return resources;
    }

    public void setResources (Resources[] resources)
    {
        this.resources = resources;
    }

    public String getHours ()
    {
        return hours;
    }

    public void setHours (String hours)
    {
        this.hours = hours;
    }

    public String getPrice_per_hour ()
    {
        return price_per_hour;
    }

    public void setPrice_per_hour (String price_per_hour)
    {
        this.price_per_hour = price_per_hour;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [resources = "+resources+", hours = "+hours+", price_per_hour = "+price_per_hour+"]";
    }
}
	

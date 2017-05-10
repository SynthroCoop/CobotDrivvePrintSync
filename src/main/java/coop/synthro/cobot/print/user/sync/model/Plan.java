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
public class Plan
{
    private String total_price_per_cycle;

    private String canceled_to;

    private Booking_credits[] booking_credits;

    private String price_per_cycle_in_cents;

    private Time_passes[] time_passes;

    private Extras[] extras;

    private String currency;

    private String cycle_duration;

    private String description;

    private String name;

    private String price_per_cycle;

    private String cancellation_period;

    private Parent_plan parent_plan;

    private String tax_rate;

    public String getTotal_price_per_cycle ()
    {
        return total_price_per_cycle;
    }

    public void setTotal_price_per_cycle (String total_price_per_cycle)
    {
        this.total_price_per_cycle = total_price_per_cycle;
    }

    public String getCanceled_to ()
    {
        return canceled_to;
    }

    public void setCanceled_to (String canceled_to)
    {
        this.canceled_to = canceled_to;
    }

    public Booking_credits[] getBooking_credits ()
    {
        return booking_credits;
    }

    public void setBooking_credits (Booking_credits[] booking_credits)
    {
        this.booking_credits = booking_credits;
    }

    public String getPrice_per_cycle_in_cents ()
    {
        return price_per_cycle_in_cents;
    }

    public void setPrice_per_cycle_in_cents (String price_per_cycle_in_cents)
    {
        this.price_per_cycle_in_cents = price_per_cycle_in_cents;
    }

    public Time_passes[] getTime_passes ()
    {
        return time_passes;
    }

    public void setTime_passes (Time_passes[] time_passes)
    {
        this.time_passes = time_passes;
    }

    public Extras[] getExtras ()
    {
        return extras;
    }

    public void setExtras (Extras[] extras)
    {
        this.extras = extras;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    public String getCycle_duration ()
    {
        return cycle_duration;
    }

    public void setCycle_duration (String cycle_duration)
    {
        this.cycle_duration = cycle_duration;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPrice_per_cycle ()
    {
        return price_per_cycle;
    }

    public void setPrice_per_cycle (String price_per_cycle)
    {
        this.price_per_cycle = price_per_cycle;
    }

    public String getCancellation_period ()
    {
        return cancellation_period;
    }

    public void setCancellation_period (String cancellation_period)
    {
        this.cancellation_period = cancellation_period;
    }

    public Parent_plan getParent_plan ()
    {
        return parent_plan;
    }

    public void setParent_plan (Parent_plan parent_plan)
    {
        this.parent_plan = parent_plan;
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
        return "ClassPojo [total_price_per_cycle = "+total_price_per_cycle+", canceled_to = "+canceled_to+", booking_credits = "+booking_credits+", price_per_cycle_in_cents = "+price_per_cycle_in_cents+", time_passes = "+time_passes+", extras = "+extras+", currency = "+currency+", cycle_duration = "+cycle_duration+", description = "+description+", name = "+name+", price_per_cycle = "+price_per_cycle+", cancellation_period = "+cancellation_period+", parent_plan = "+parent_plan+", tax_rate = "+tax_rate+"]";
    }
}

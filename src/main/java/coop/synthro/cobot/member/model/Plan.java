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
public class Plan {

    private String total_price_per_cycle;

    private String canceled_to;

    private Booking_credits[] booking_credits;

    private Booking_passes[] booking_passes;

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

    private String terms;

    private String accounting_code;

    private String welcome_message;

    public String getTotal_price_per_cycle() {
        return total_price_per_cycle;
    }

    public void setTotal_price_per_cycle(String total_price_per_cycle) {
        this.total_price_per_cycle = total_price_per_cycle;
    }

    public String getCanceled_to() {
        return canceled_to;
    }

    public void setCanceled_to(String canceled_to) {
        this.canceled_to = canceled_to;
    }

    public Booking_credits[] getBooking_credits() {
        return booking_credits;
    }

    public void setBooking_credits(Booking_credits[] booking_credits) {
        this.booking_credits=booking_credits;
    }

    public String getPrice_per_cycle_in_cents() {
        return price_per_cycle_in_cents;
    }

    public void setPrice_per_cycle_in_cents(String price_per_cycle_in_cents) {
        this.price_per_cycle_in_cents = price_per_cycle_in_cents;
    }

    public Time_passes[] getTime_passes() {
        return time_passes;
    }

    public void setTime_passes(Time_passes[] time_passes) {
        this.time_passes= time_passes;
    }

    public Extras[] getExtras() {
        return extras;
    }

    public void setExtras(Extras[] extras) {
        this.extras=extras;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCycle_duration() {
        return cycle_duration;
    }

    public void setCycle_duration(String cycle_duration) {
        this.cycle_duration = cycle_duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice_per_cycle() {
        return price_per_cycle;
    }

    public void setPrice_per_cycle(String price_per_cycle) {
        this.price_per_cycle = price_per_cycle;
    }

    public String getCancellation_period() {
        return cancellation_period;
    }

    public void setCancellation_period(String cancellation_period) {
        this.cancellation_period = cancellation_period;
    }

    public Parent_plan getParent_plan() {
        return parent_plan;
    }

    public void setParent_plan(Parent_plan parent_plan) {
        this.parent_plan = parent_plan;
    }

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }

    @Override
    public String toString() {
        return "ClassPojo [total_price_per_cycle = " + getTotal_price_per_cycle() + ", canceled_to = " + getCanceled_to() + ", booking_credits = " + getBooking_credits() + ", price_per_cycle_in_cents = " + getPrice_per_cycle_in_cents() + ", time_passes = " + getTime_passes() + ", extras = " + getExtras() + ", currency = " + getCurrency() + ", cycle_duration = " + getCycle_duration() + ", description = " + getDescription() + ", name = " + getName() + ", price_per_cycle = " + getPrice_per_cycle() + ", cancellation_period = " + getCancellation_period() + ", parent_plan = " + getParent_plan() + ", tax_rate = " + getTax_rate() + "]";
    }


    /**
     * @return the booking_passes
     */
    public Booking_passes[] getBooking_passes() {
        return booking_passes;
    }

    /**
     * @param booking_passes the booking_passes to set
     */
    public void setBooking_passes(Booking_passes[] booking_passes) {
        this.booking_passes = booking_passes;
    }


    /**
     * @return the terms
     */
    public String getTerms() {
        return terms;
    }

    /**
     * @param terms the terms to set
     */
    public void setTerms(String terms) {
        this.terms = terms;
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
     * @return the welcome_message
     */
    public String getWelcome_message() {
        return welcome_message;
    }

    /**
     * @param welcome_message the welcome_message to set
     */
    public void setWelcome_message(String welcome_message) {
        this.welcome_message = welcome_message;
    }
}

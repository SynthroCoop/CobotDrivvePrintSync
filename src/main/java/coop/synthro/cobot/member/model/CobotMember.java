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
public class CobotMember
{
    private String phone;

    private String canceled_to;

    //private String upcoming_plan;

    private String starts_at;

    private String confirmed_at;

    private String internal_memo;

    private String next_invoice_at;

    private String id;

    private String picture;

    private String[] billing_emails;

    private String customer_number;

    private Plan plan;

    private String email;

    private Address address;

    private String name;

    private String charge_taxes;

    private Payment_method payment_method;

    private User user;
    
    private String first_invoice_at;
    
    private String joined_at;
    
    private String requested_starts_at;
    
    private String tax_id;
    
    private String newsletter_approval;
    

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getCanceled_to ()
    {
        return canceled_to;
    }

    public void setCanceled_to (String canceled_to)
    {
        this.canceled_to = canceled_to;
    }

//    public String getUpcoming_plan ()
//    {
//        return upcoming_plan;
//    }
//
//    public void setUpcoming_plan (String upcoming_plan)
//    {
//        this.upcoming_plan = upcoming_plan;
//    }

    public String getStarts_at ()
    {
        return starts_at;
    }

    public void setStarts_at (String starts_at)
    {
        this.starts_at = starts_at;
    }

    public String getConfirmed_at ()
    {
        return confirmed_at;
    }

    public void setConfirmed_at (String confirmed_at)
    {
        this.confirmed_at = confirmed_at;
    }

    public String getInternal_memo ()
    {
        return internal_memo;
    }

    public void setInternal_memo (String internal_memo)
    {
        this.internal_memo = internal_memo;
    }

    public String getNext_invoice_at ()
    {
        return next_invoice_at;
    }

    public void setNext_invoice_at (String next_invoice_at)
    {
        this.next_invoice_at = next_invoice_at;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPicture ()
    {
        return picture;
    }

    public void setPicture (String picture)
    {
        this.picture = picture;
    }

    public String[] getBilling_emails ()
    {
        return billing_emails;
    }

    public void setBilling_emails (String[] billing_emails)
    {
        this.billing_emails = billing_emails;
    }

    public String getCustomer_number ()
    {
        return customer_number;
    }

    public void setCustomer_number (String customer_number)
    {
        this.customer_number = customer_number;
    }

    public Plan getPlan ()
    {
        return plan;
    }

    public void setPlan (Plan plan)
    {
        this.plan = plan;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress (Address address)
    {
        this.address = address;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCharge_taxes ()
    {
        return charge_taxes;
    }

    public void setCharge_taxes (String charge_taxes)
    {
        this.charge_taxes = charge_taxes;
    }

    public Payment_method getPayment_method ()
    {
        return payment_method;
    }

    public void setPayment_method (Payment_method payment_method)
    {
        this.payment_method = payment_method;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [phone = "+phone+", canceled_to = "+canceled_to+", starts_at = "+starts_at+", confirmed_at = "+confirmed_at+", internal_memo = "+internal_memo+", next_invoice_at = "+next_invoice_at+", id = "+id+", picture = "+picture+", billing_emails = "+billing_emails+", customer_number = "+customer_number+", plan = "+plan+", email = "+email+", address = "+address+", name = "+name+", charge_taxes = "+charge_taxes+", payment_method = "+payment_method+", user = "+user+"]";
    }

    /**
     * @return the first_invoice_at
     */
    public String getFirst_invoice_at() {
        return first_invoice_at;
    }

    /**
     * @param first_invoice_at the first_invoice_at to set
     */
    public void setFirst_invoice_at(String first_invoice_at) {
        this.first_invoice_at = first_invoice_at;
    }

    /**
  
    /**
     * @return the requested_starts_at
     */
    public String getRequested_starts_at() {
        return requested_starts_at;
    }

    /**
     * @param requested_starts_at the requested_starts_at to set
     */
    public void setRequested_starts_at(String requested_starts_at) {
        this.requested_starts_at = requested_starts_at;
    }

    /**
     * @return the tax_id
     */
    public String getTax_id() {
        return tax_id;
    }

    /**
     * @param tax_id the tax_id to set
     */
    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    /**
     * @return the joined_at
     */
    public String getJoined_at() {
        return joined_at;
    }

    /**
     * @param joined_at the joined_at to set
     */
    public void setJoined_at(String joined_at) {
        this.joined_at = joined_at;
    }

    /**
     * @return the newsletter_approval
     */
    public String getNewsletter_approval() {
        return newsletter_approval;
    }

    /**
     * @param newsletter_approval the newsletter_approval to set
     */
    public void setNewsletter_approval(String newsletter_approval) {
        this.newsletter_approval = newsletter_approval;
    }
}

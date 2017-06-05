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
public class Payment_method
{
    private String automated;

    private String instructions;

    private String name;

    public String getAutomated ()
    {
        return automated;
    }

    public void setAutomated (String automated)
    {
        this.automated = automated;
    }

    public String getInstructions ()
    {
        return instructions;
    }

    public void setInstructions (String instructions)
    {
        this.instructions = instructions;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [automated = "+automated+", instructions = "+instructions+", name = "+name+"]";
    }
}


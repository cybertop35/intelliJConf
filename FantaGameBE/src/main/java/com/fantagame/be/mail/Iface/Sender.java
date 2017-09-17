package com.fantagame.be.mail.Iface;

import java.util.Map;


/**
 * Sends an e-mail message.
 * 
 * @author Marco Calabretta
 */
public interface Sender { 

    /**
     * Sends e-mail using Velocity template for the body and 
     * the properties passed in as Velocity variables.
     * 
     * @param   msg                 The e-mail message to be sent, except for the body.
     * @param   hTemplateVariables  Variables to use when processing the template. 
     */
    public void send(final CustomMailMessage msg,final String template,final Map<Object, Object> hTemplateVariables);
    
}
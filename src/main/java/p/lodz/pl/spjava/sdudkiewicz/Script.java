/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz;

import java.util.logging.Logger;

import p.lodz.pl.spjava.sdudkiewicz.configs.MvcConfig;

/**
 *
 * @author Sylwester
 */
public class Script {
	private static final Logger LOGGER = Logger.getLogger( Script.class.getName() );
	
    public void startDomain(String subject){
        LOGGER.info("Initialize start domian: "+ subject);
        LOGGER.info("Finished start domian: "+ subject);
    }
    
    
    public void stopDomain(String subject){
        LOGGER.info("Initialize stop domian: "+ subject);
        LOGGER.info("Finished stop domian: "+ subject);
    }
    
}

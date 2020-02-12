/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.illumio.firewall;

/**
 *
 * @author pavanrao
 */
public class Util {
    
     /**
     * 
     * @param IP - IP address which needs to be converted to integer
     * @return 
     */
    public static Double convert_IP_to_Int(String IP) {
        
        /**
         * 1. To convert an IP to integer, break the IP address into 4 octets 
         * 2. Apply the formula: (first octet * 256³) + (second octet * 256²) + (third octet * 256) + (fourth octet)
         * Example: 24.218.111.104 => (24 * 256³) + (218 * 256²) + (111 * 256) + (104)
         */
        String[] partsOfIP = IP.split("\\.");
        Double part1, part2, part3, part4;
        part1 = Integer.parseInt(partsOfIP[0]) * Math.pow(256, 3);
        part2 = Integer.parseInt(partsOfIP[1]) * Math.pow(256, 2);
        part3 = Integer.parseInt(partsOfIP[2]) * Math.pow(256, 1);
        part4 = Double.parseDouble(partsOfIP[3]);
        return part1+part2+part3+part4;
        
    }
}
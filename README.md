# Illumio-Coding-Challenge

## Problem statement: 
* Real-world firewalls support both “allow” and “block” rules, and their ordering is important in determining the fate of a packet. In this coding exercise, we will greatly simplify this model by only supporting “allow” rules. If a packet does not match any “allow” rule, then we assume it will be blocked.
* The rules are mentioned in rules.csv

## Solution:
* All the rules are added into an ArrayList
* To verify whether an input packet is allowed or not, 
    1. Retrieve each rule from the array list
    2. Check for validity of direction and protocol 
    3. Check for validity of port
        - if the port in rule is range, input packet port should fall in the range
        - else the port should match exactly with input packet
    4. Check for validity of IP address
        - convert IP Address to integer (for both range and fixed IPs)
        - If range of IPs are given, input IP should fall within the range
        - Else the IP should match exactly with the input IP
    5. Repeat until a rule is satisfied

## Reference:
* To convert from IP address into integer, the formula in http://www.aboutmyip.com/AboutMyXApp/IP2Integer.jsp is used

## Test cases considered:
1. Input packet which complies to atleast one of the rules
2. Input packet whose port and IP fall within the give range
3. Input packet whose port and IP are equal to the max and min values in the range
4. Input packet which satisfies two of the given rules
5. Input packet whose port and IP do not satisfy any of the rules

## Added features:
* At frequent intervals, the status is logged: Adding rule to list, any errors while processing the CSV and the final status: Accepted/blocked
* Necessary JAVA docs to make every function and logic easily understandable
* Enhanced on code reusability by adding necessary util and validators

## Scope of improvement:
* Instead of an array list, hash set could be used to make sure that no duplicate rules are added
* Instead of iterating through each rule linearly, a TRIE data structure could be used to make the code run faster
* Unit test cases could be added for validators and util classes
* Component test case to test the overall flow instead of individual unit test cases 

## Teams interested:
* Policy
* Platform 


package com.driver;

import java.util.HashMap;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only



    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name, balance, 5000);
        if(balance<5000) {
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId= tradeLicenseId;
    }

    public CurrentAccount(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public String getTradeLicenseId() {
        //System.out.println(this.tradeLicenseId);
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception'
        char [] id= tradeLicenseId.toCharArray();
        int n= id.length;
        boolean isValid=true;
        for(int i=1; i<n; i++){
            if(id[i]==id[i-1]){
                isValid=false;
                break;
            }
        }
        if(isValid) return;
        int map[] = new int[26];
        int max=0;
        for(int i=0; i<n; i++){
            map[id[i]-'A']++;
        }

        PriorityQueue<pair> pq= new PriorityQueue<pair>((a, b)->{
            return a.frq-b.frq;
        });

        for(int i=0; i<map.length; i++){
            if(map[i]>0) pq.add(new pair((char) (i+'A'), map[i]));
            if(max<map[i]){
                max= map[i];
            }
        }
        if(max>(n+1)/2){
            throw new Exception("Valid License can not be generated");
        }

        int  i=0;
        while(!pq.isEmpty()){
            pair p=pq.remove();
            while(p.frq>0){
                if(i>=n) i=1;
                id[i]= p.letter;
                p.frq--;
                i+=2;
            }
        }

        this.setTradeLicenseId(id.toString());
    }
    class pair {
        char letter;
        int frq;

        pair(char letter, int frq){
            this.letter=letter;
            this.frq= frq;
        }

    }

}

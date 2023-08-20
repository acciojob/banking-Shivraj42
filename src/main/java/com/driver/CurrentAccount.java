package com.driver;

import java.util.HashMap;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super();
        if(balance<5000){
            throw new Exception("Insufficient Balance");
        }
        this.setName(name);
        this.setBalance(balance);
        this.setMinBalance(5000);
        this.tradeLicenseId= tradeLicenseId;
    }

    public CurrentAccount(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public String getTradeLicenseId() {
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
        int maxi=0;
        for(int i=0; i<n; i++){
            map[id[i]-'a']++;
            if(max<map[id[i]-'a']){
                max= map[id[i]-'a'];
                maxi=id[i]-'a';
            }
        }
        if(max>(n+1)/2){
            throw new Exception("Valid License can not be generated");
        }
        int i=0;
        while(map[maxi]>0){
            id[i]=(char)(maxi+'a');
            map[maxi]--;
            i+=2;
        }
        for(int j=0; j<map.length; j++){
            while(map[j]>0){
                if(i>=n) i=1;
                id[i]=(char)(j+'a');
                map[j]--;
                i+=2;
            }
        }

        this.setTradeLicenseId(id.toString());
    }

}

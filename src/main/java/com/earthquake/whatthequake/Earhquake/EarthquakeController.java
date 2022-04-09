package com.earthquake.whatthequake.Earhquake;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import models.Earthquake;

@Controller
public class EarthquakeController{
    private Integer pastDays= 1;
    
    @RequestMapping(value = "/result{country}{days}", method = RequestMethod.GET)
    public String getAllEarthquakes(Model model,@RequestParam(required = false, value="country") String country,
                                                @RequestParam(required = false, value="days") String days){

        pastDays = Integer.parseInt(days);
        EarthquakeResource eqResource = new EarthquakeResource();
        List<Earthquake> eqList = eqResource.getEartquakeList(pastDays);

        if(eqList == null){
            model.addAttribute("exceedMessage", "There are more than 20.000 earthquakes. Please reduce the number of days and try again.");
            return"index.html";
        }

        List<Earthquake> orgEqList = new ArrayList<>(eqList);
        fixPlaces(eqList);
        eqList= filterListByCountry(eqList,country);

        if(eqList.size() == 0){
            List<String> recommendationList = (fillRecommendation(orgEqList)).stream().sorted().collect(Collectors.toList());  
            model.addAttribute("countryList", recommendationList);
            model.addAttribute("days", days);
            model.addAttribute("title", String.format("No Earthquakes were recorded past %s days for %s.", days, country));
            return "empty";
        }        
        
        fixDates(eqList);
        fixMagnitude(eqList);
        model.addAttribute("list",eqList);
        String pageTitle = String.format("%s Earthquakes happened in %s for the last %s days ", eqList.size(),country, days);
        model.addAttribute("title", pageTitle);
        return "result";
    }

    private List<String> fillRecommendation(List<Earthquake> eqList) {
        List<String> retList = new ArrayList<String>();
        for(Earthquake eq : eqList){
            if(eq.getProperties().getPlace()==null){
                continue;
            }
            if(!retList.contains(eq.getProperties().getCountry())){
                retList.add(eq.getProperties().getCountry());
            }

        }
        return retList;
    }

    private List<Earthquake> filterListByCountry(List<Earthquake> eqList,String country) {
        List<Earthquake> retList = new ArrayList<Earthquake>();
        for(Earthquake eq : eqList){
            if(country.equalsIgnoreCase(eq.getProperties().getCountry())){
                retList.add(eq);
            }
        }
        return retList;
    }

    private List<Earthquake>fixPlaces(List<Earthquake> eqList){
        for(Earthquake eq : eqList){

            if(eq.getProperties().getPlace() !=null){

                String[] strList = eq.getProperties().getPlace().split(",");
                if(strList.length <2){
                    eq.getProperties().setPlace(strList[0]);
                    eq.getProperties().setCountry(strList[0]);
                }
                else{
                    eq.getProperties().setPlace(strList[0]);
                    eq.getProperties().setCountry(strList[1].trim());
                }
            }
            
        }

        return eqList;
    }

    private List<Earthquake> fixDates(List<Earthquake> eqList){
        for(Earthquake eq : eqList){
            DateFormat obj = new SimpleDateFormat("dd MMM yyyy/HH:mm:ss"); 
            Date d = new Date(eq.getProperties().getTime()); 
            eq.getProperties().setDate(obj.format(d).split("/")[0]);
            eq.getProperties().setClock(obj.format(d).split("/")[1]); 
        }
        return eqList;
    }

    private List<Earthquake> fixMagnitude( List<Earthquake> eqList){
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        for(Earthquake eq : eqList){
            try{
                eq.getProperties().setMag(numberFormat.format(Double.parseDouble(eq.getProperties().getMag())));
            }catch(Exception e){

            }
        }
        return eqList;
    }
    
}
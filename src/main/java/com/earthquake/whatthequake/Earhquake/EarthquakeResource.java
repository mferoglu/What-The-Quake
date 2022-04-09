package com.earthquake.whatthequake.Earhquake;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import models.ApiResponse;
import models.Earthquake;

@RestController
public class EarthquakeResource{

        private RestTemplate restTemplate;
        private String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=%s&endtime=%s";


        public List<Earthquake> getEartquakeList(Integer pastDays){
            restTemplate = new RestTemplate();
            ApiResponse response = null;
            try{
            response = restTemplate.getForObject(getUrl(pastDays), ApiResponse.class);
            }
            catch(Exception e){
                return null;
            }
            return response.getFeatures();
        }

        public String getUrl(Integer pastDays){
            List<String> dates = getDates(pastDays);
            URL = String.format(URL, dates.get(0),dates.get(1));
            return URL;
        }
        
        private List<String> getDates(Integer pastDays){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            Date endCal = cal.getTime();
            String endDate = dateFormat.format(endCal);
            cal.add(Calendar.DATE, pastDays*-1);
            Date beginCal = cal.getTime();
            String beginDate = dateFormat.format(beginCal); 
            List<String> dates = new ArrayList<String>();
            dates.add(beginDate);
            dates.add(endDate);
            return dates;

        }
}
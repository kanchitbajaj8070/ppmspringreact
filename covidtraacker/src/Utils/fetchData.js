
import  axios from 'axios';
import {API_URL} from "../Constants";
import React from "react";

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
export  const fetchData=async ()=>
{
      try{//happy path
         const response= await axios.get(API_URL);
     /*    for (let i = 0; i < 5; i++) {
              if (i === 3)
                  await sleep(20000); // for seeing spinner in action
              console.log(i);
          }*/
         return response.data;
      }
      catch (e) {
          window.alert("cant fetch the data at the moment");

      }
}
export const fetchDailyData=async ()=>{
    try
    {
        const response= await axios.get(`${API_URL}/daily`);
        console.log(response.data);
        const modifiedData=response.data.map((dailyData)=>(
            {
                confirmed:dailyData.confirmed.total,
                deaths:dailyData.deaths.total,
                date:dailyData.reportDate
            }
        ));
        console.log(modifiedData)
        return modifiedData;
    }
    catch (e) {
        window.alert("failed to load daily data");
    }
}

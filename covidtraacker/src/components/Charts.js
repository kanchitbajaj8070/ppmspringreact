import React,{useState,useEffect} from "react";
import {fetchDailyData} from "../Utils/fetchData";
import {Line,Bar} from "react-chartjs-2";
import '../assests/css/charts.css'
const Charts =()=>
{
    const [dailyData,setDailyData]=useState([]);

    useEffect(()=>
        {
            const fetchApi=async ()=>
            {
                const dailyData=await  fetchDailyData();
                console.log( dailyData);
                setDailyData( dailyData);
            }
            fetchApi();
        }
    ,[]);
    const lineChart =(dailyData.length!==0?
            <div>
            <Line data={{
                labels:dailyData.map((d) => d.date) ,
                datasets:[{
                    data:dailyData.map((d)=> d.confirmed),
                    label:'Confirmed Cases',
                    borderColor:'#3333ff',
                    fill:true
                },{

                        data:dailyData.map((d)=> d.deaths),
                        label: 'Deaths',
                        borderColor:"rgba(255,0,0,0.5)",
                        fill:true

                }],


            }} />
            </div>:<div > No data for displaying charts </div>
        );

    return (
        <div className={"container"}>
            {lineChart}
        </div>
    );

}
export default Charts;
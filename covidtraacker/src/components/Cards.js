import React from "react";
import {Card,CardContent,Typography,Grid} from "@material-ui/core";
import CountUp from "react-countup";
import '../assests/css/cards.css'
const Cards=(props)=>
{
    console.log(props.data);
    const{confirmed, recovered,deaths,lastUpdate}=props.data;
    return(
        <div className="container">
            <Grid container spacing={3} justify="center">
                <Grid item component={Card} xs={12} md={3} className="p-2 m-2 infected ">
                    <CardContent>
                        <Typography colors="textSecondary" gutterBottom >Infected</Typography>
                        <Typography variant="h5">
                            <CountUp start={0} end={confirmed} duration={2.5}
                            separator="," />
                            </Typography>
                        <Typography color="textSecondary">Last Update On {lastUpdate} </Typography>
                        <Typography variant="body2">Confirmed Cases of covid-19</Typography>
                    </CardContent>
                </Grid>
                <Grid item component={Card} xs={12} md={3} className="p-2 m-2  recovered">
                    <CardContent>
                        <Typography colors="textSecondary" gutterBottom >Recovered </Typography>
                        <Typography variant="h5">
                            <CountUp start={0} end={recovered} duration={2.5}
                                     separator="," />
                        </Typography>
                        <Typography color="textSecondary">Last Update On {lastUpdate} </Typography>
                        <Typography variant="body2">Recovered Cases of covid-19</Typography>
                    </CardContent>
                </Grid>

                <Grid item component={Card} xs={12} md={3} className="p-2 m-2  deaths">
                    <CardContent>
                        <Typography colors="textSecondary" gutterBottom >Deaths</Typography>
                        <Typography variant="h5">
                            <CountUp start={0} end={deaths} duration={2.5}
                                     separator=","/></Typography>
                        <Typography color="textSecondary">Last Update On {lastUpdate} </Typography>
                        <Typography variant="body2">Deaths by covid-19</Typography>
                    </CardContent>
                </Grid>
            </Grid>
        </div>
    );

}
export default Cards;
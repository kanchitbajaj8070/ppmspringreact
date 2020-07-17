import React, {Component} from 'react';
import axios from 'axios'
import {API_URL} from "../Constants";
import {fetchData} from "../Utils/fetchData";
import CircularProgress from "@material-ui/core/CircularProgress";
import Cards from "./Cards";
import Chart from "./Charts";
import Charts from "./Charts";
class Home extends Component {
    constructor(props) {
        super(props);
        this.state={
            confirmed:"",
            recovered:"",
            deaths:"",
            lastUpdate:"",
            isloading:true
        }
    }
    async componentDidMount() {
        const res= await fetchData() ;
        this.setState({
            confirmed:res.confirmed.value,
            recovered:res.recovered.value,
            deaths:res.deaths.value,
            lastUpdate:res.lastUpdate.substring(0,10),
            isloading:false
        });
    }

    render() {
        const{confirmed, recovered,deaths,lastUpdate,isloading}=this.state;
        console.log(this.state)
        return (
            <div className="container">
                {isloading?(
                    <div>
                <CircularProgress color={"primary"}/>
                    </div>):(
                        <div>
                        <Cards data={this.state}/>
                        <div className="bg-light dark-overlay shadow-lg container border-all">
                            <Charts/>
                        </div>
                    </div>

                    )}
            </div>

        );
    }
}

export default Home;
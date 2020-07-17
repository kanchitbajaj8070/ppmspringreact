import React, {Component} from 'react';
import axios from 'axios'

class Home extends Component {
    constructor(props) {
        super(props);
        this.state={
            confirmed:"",
            recovered:"",
            deaths:""
        }
    }
    async componentDidMount() {
        const res=axios.get("https://covid19.mathdro.id/api");
        const newObj={};
        newObj.comfirmed=(await res).data.confirmed.value;
        newObj.recovered=(await res).data.recovered.value;
        newObj.deaths=(await res).data.deaths.value;
        this.setState((newObj));
    }

    render() {
        const{confirmed, recovered,deaths}=this.state;
        console.log(this.state)
        return (
            <div>
<h1>{confirmed}
</h1>
                <h1>{recovered}
                </h1>
                <h1>{deaths}
                </h1>
            </div>
        );
    }
}

export default Home;
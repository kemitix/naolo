import React, {useEffect, useState} from "react";
import "./App.css";
import Header from "./components/Header";
import Navigation from "./components/Navigation";
import MainBody from "./components/MainBody";
import Footer from "./components/Footer";
import "tachyons/css/tachyons.min.css";

const SERVER_URI = "http://localhost:8080/naolo";

const App = () => {
    const [features, setFeatures] = useState([]);
    useEffect(() => {
        fetch(SERVER_URI + "/api/ui/features")
            .then(response => response.json())
            .then(_features => setFeatures(_features));
    }, []);
    return (
        <div className="App">
            <Header/>
            <div className="cf">
                <Navigation features={features}
                            serverUri={SERVER_URI}/>
                <MainBody/>
            </div>
            <Footer/>
        </div>
    );
};

export default App;

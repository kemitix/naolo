import React, {useEffect, useState} from "react";
import "./App.css";
import Header from "./components/Header";
import Navigation from "./components/Navigation";
import MainBody from "./components/MainBody";
import Footer from "./components/Footer";
import "tachyons/css/tachyons.min.css";
import {HashRouter as Router} from "react-router-dom";

const SERVER_URI = "http://localhost:8080/naolo";

const ServerError = () =>
    <>
        <h1>Server Error</h1>
        <br/>
        <p>Error connecting to server.</p>
    </>;

const App = () => {
    const [features, setFeatures] = useState([]);
    const [serverOkay, setServerOkay] = useState(false);//until proven otherwise
    useEffect(() => {
        fetch(SERVER_URI + "/api/ui/features")
            .then(response => response.json())
            .then(_features => {
                setFeatures(_features);
                setServerOkay(true);
            })
            .catch(reason => setServerOkay(false));
    }, []);
    const AppPage = () => {
        if (serverOkay) return (<><Navigation features={features} serverUri={SERVER_URI}/><MainBody/></>);
        return <ServerError/>
    }
    return (
        <Router>
            <div className="App">
                <Header/>
                <div className="cf">
                    <AppPage/>
                </div>
                <Footer/>
            </div>
        </Router>
    );
};

export default App;

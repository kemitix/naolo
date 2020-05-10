import React, {useEffect, useState} from "react";
import "./App.css";
import Header from "./components/Header";
import Navigation, {Feature} from "./components/Navigation";
import MainBody from "./components/MainBody";
import Footer from "./components/Footer";
import "tachyons/css/tachyons.min.css";
import {HashRouter as Router} from "react-router-dom";

// TODO: remove this or get it from environment
const SERVER_URI = "http://localhost:8080/naolo";
const FEATURES_URI = SERVER_URI + "/api/ui/features";

interface AppPageProps {
    features: Array<Feature>
}

const AppPage = (props: AppPageProps) => {
    if (props.features.length === 0) {
        return (
            <>
                <h1>Server Error</h1>
                <p>There are no features enabled in the server.</p>
            </>
        )
    }
    return(
        <>
            <Navigation features={props.features} serverUri={SERVER_URI}/>
            <MainBody/>
        </>
    );
}

function fetchUri(uri: string) {
    console.log(`GET ${uri}`);//non-production
    return fetch(uri);
}

const App = () => {
    const [features, setFeatures] = useState([]);
    useEffect(() => {
        (async () =>
            (await fetchUri(FEATURES_URI))
                .json()
                .then(res => {
                    console.log(`Got ${res.length} features`); //non-production
                    setFeatures(res);
                }))();
    }, []);
    return (
        <Router>
            <div className="App">
                <Header/>
                <div className="cf">
                    <AppPage features={features}/>
                </div>
                <Footer/>
            </div>
        </Router>
    );
};

export default App;

import React, {useEffect, useState} from "react";
import "./App.css";
import Header from "./components/Header";
import Navigation from "./components/Navigation";
import MainBody from "./components/MainBody";
import Footer from "./components/Footer";
import "tachyons/css/tachyons.min.css";
import {HashRouter as Router} from "react-router-dom";

// TODO: remove this or get it from environment
const SERVER_URI = "http://localhost:8080/naolo";

const App = () => {
    const [hasError, setErrors] = useState({});
    const [features, setFeatures] = useState([]);
    useEffect(() => {
        (async function () {
            (await fetch(SERVER_URI + "/api/ui/features"))
                .json()
                .then(res => setFeatures(res))
                .catch(err => setErrors(err));
        })();
    }, []);
    const AppPage = () => {
        if (features.length === 0) {
            return (
                <>
                    <h1>Server Error</h1>
                    <p>There are no features enabled in the server.</p>
                </>
            )
        }
        return(
            <>
                <Navigation features={features} serverUri={SERVER_URI}/>
                <MainBody/>
            </>
        );
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

import React, {useEffect, useState} from "react";
import "./App.css";
import Header from "./components/Header";
import Navigation from "./components/Navigation";
import MainBody from "./components/MainBody";
import Footer from "./components/Footer";
import "tachyons/css/tachyons.min.css";

const API_SERVER = "http://localhost:8080";

const App = () => {
    const [features, setFeatures] = useState([]);
    useEffect(() => {
        fetch(API_SERVER + "/ui/features")
            .then(response => response.json())
            .then(_features => setFeatures(_features));
    }, []);
    return (
        <div className="App">
            <Header/>
            <div className="cf">
                <Navigation features={features} api={API_SERVER}/>
                <MainBody/>
            </div>
            <Footer/>
        </div>
    );
};

export default App;
